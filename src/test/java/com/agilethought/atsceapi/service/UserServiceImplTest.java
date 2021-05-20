package com.agilethought.atsceapi.service;

import static com.agilethought.atsceapi.exception.ErrorMessage.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.agilethought.atsceapi.validator.user.LoginValidator;
import com.agilethought.atsceapi.validator.user.NewUserValidator;
import com.agilethought.atsceapi.validator.user.UpdateUserValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.agilethought.atsceapi.dto.user.*;
import com.agilethought.atsceapi.exception.NotFoundException;
import com.agilethought.atsceapi.exception.UnauthorizedException;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.service.implementation.UserServiceImpl;
import com.agilethought.atsceapi.service.security.RsaPasswordEncoder;
import com.agilethought.atsceapi.repository.UserRepository;

import ma.glasnost.orika.MapperFacade;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MapperFacade orikaMapperFacade;

    @Mock
    private LoginValidator loginValidator;

    @Mock
    private NewUserValidator newUserValidator;

    @Mock
    private UpdateUserValidator updateUserValidator;
    
    @Mock
    private RsaPasswordEncoder rsaPasswordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsers() {

        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        when(orikaMapperFacade.mapAsList(anyList(), any())).thenReturn(new ArrayList<>());
        List<UserDTO> testResult = userService.getAllUsers();
        assertNotNull(testResult);

    }

    @Test
    public void testLoginUserWithValidCredentialsAndAvailable() {

        // Given
        LoginRequest mockLoginRequest = new LoginRequest();
        mockLoginRequest.setEmail("");
        mockLoginRequest.setPassword("");

        
        User mockUser = new User();
        mockUser.setStatus(1);

        when(rsaPasswordEncoder.decode(any())).thenReturn("4giLeThought");
        doNothing().when(loginValidator).validate(any());
        when(userRepository.findByEmail(mockLoginRequest.getEmail()))
                .thenReturn(mockUser);
        when(rsaPasswordEncoder.matches(any(), any())).thenReturn(true);
        when(orikaMapperFacade.map(any(), any())).thenReturn(new LoginResponse());
        LoginResponse testResult = userService.loginUser(mockLoginRequest);
        assertNotNull(testResult);

    }

    @Test
    public void testLoginUserWithValidCredentialsAndUnavailable() {

        // Given
        LoginRequest mockLoginRequest = new LoginRequest();
        mockLoginRequest.setEmail("");
        mockLoginRequest.setPassword("");

        User mockUser = new User();
        mockUser.setStatus(0);

        // Then
        doNothing().when(loginValidator).validate(any());
        when(userRepository.findByEmail(anyString()))
                .thenReturn(mockUser);
        
        when(rsaPasswordEncoder.matches(any(), any())).thenReturn(true);
        UnauthorizedException thrownException = assertThrows(
                UnauthorizedException.class,
                () -> userService.loginUser(mockLoginRequest)
        );
        assertEquals(
                String.format(UNAVAILABLE_ENTITY, USER),
                thrownException.getMessage()
        );

    }

    @Test
    public void testLoginUserWithInvalidCredentials() {

        // Given
        LoginRequest mockLoginRequest = new LoginRequest();
        mockLoginRequest.setEmail("");
        mockLoginRequest.setPassword("");

        // Then
        doNothing().when(loginValidator).validate(any());
        when(userRepository.findByEmail(anyString()))
                .thenReturn(new User());
        UnauthorizedException thrownException = assertThrows(
                UnauthorizedException.class,
                () -> userService.loginUser(mockLoginRequest)
        );
        assertEquals(
                INVALID_CREDENTIALS,
                thrownException.getMessage()
        );
    }

    @Test
    public void testGetUserByIdSuccessfully() {

        when(userRepository.findById(anyString())).thenReturn(Optional.of(new User()));
        when(orikaMapperFacade.map(any(), any())).thenReturn(new UserDTO());
        UserDTO testResult = userService.getUserById(anyString());
        assertNotNull(testResult);

    }

    @Test
    public void testGetUserByIdWithIdNotFound() {

        when(userRepository.findById(anyString())).thenReturn(Optional.empty());
        NotFoundException thrownException = assertThrows(
                NotFoundException.class,
                () -> userService.getUserById(anyString())
        );
        assertEquals(
                String.format(NOT_FOUND_RESOURCE, USER, ""),
                thrownException.getMessage()
        );

    }

    @Test
    public void testDeleteUserByIdSuccessfully() {

        when(userRepository.findById(anyString())).thenReturn(Optional.of(new User()));
        doNothing().when(userRepository).deleteById(anyString());
        userService.deleteUserById(anyString());
        verify(userRepository).deleteById(anyString());

    }

    @Test
    public void testDeleteUserByIdWithIdNotFound() {

        when(userRepository.findById(anyString())).thenReturn(Optional.empty());
        doNothing().when(userRepository).deleteById(anyString());
        NotFoundException thrownException = assertThrows(
                NotFoundException.class,
                () -> userService.deleteUserById(anyString())
        );
        assertEquals(
                String.format(NOT_FOUND_RESOURCE, USER, ""),
                thrownException.getMessage()
        );

    }

    @Test
    public void testCreateUserSuccessfully() {

        // Given
        User mockCreateUser = new User();
        mockCreateUser.setFirstName("");
        mockCreateUser.setLastName("");
        mockCreateUser.setEmail("");

        // Then
        when(orikaMapperFacade.map(any(NewUserRequest.class), any()))
                .thenReturn(mockCreateUser);
        doNothing().when(newUserValidator).validate(any());
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(new User());
        when(orikaMapperFacade.map(any(User.class), any())).thenReturn(new NewUserResponse());
        NewUserRequest newUserRequest = new NewUserRequest();
        newUserRequest.setType(1);
        newUserRequest.setFirstName("Gabriel");
        newUserRequest.setLastName("Cordero");
        newUserRequest.setEmail("gabcor@gmail.com");
        //Original: Password: 4giLeThought
        String encrytedPassword = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAt11+BTT9iuYCw8srSHH23MyK8A9052WeGQOtUUVCVSju7jdpE70D7Sd2FDh0kfJBd8YV5EgXYRTgPIGIo/azqUHwi6ga0l/CQqnzfrbzVGzs5GG46uDiIbkTy0ZLgPyuWx2um1mzYCyGDMGOcnipl3O9Em2ynkfgbZWvHPRMDUhYve3c+/SI5b4J2L9Be1J5rpNFz254d10diULe+QxaLtJn0Mokh7o5ABy8GTsTXMtDogXjcQARDoFS+pbCNfIzasU14sSMlejkTrqerZLHg8QX436ap8ZR2btAiBGcppAZbW+Jz5c7LHx5cu8ZqEagS61i7KVyubA5S6HZMhctDQIDAQAB";
        newUserRequest.setPassword(encrytedPassword);
        newUserRequest.setStatus(1);
        assertNotNull(userService.createUser(newUserRequest));

    }

    @Test
    public void testUpdateUserByIdSuccessfully() {

        // Given
        User mockUpdateUser = new User();
        mockUpdateUser.setFirstName("");
        mockUpdateUser.setLastName("");
        mockUpdateUser.setEmail("");

        // Then
        when(orikaMapperFacade.map(any(UpdateUserRequest.class), any()))
                .thenReturn(mockUpdateUser);
        doNothing().when(updateUserValidator).validate(any());
        when(userRepository.save(any())).thenReturn(new User());
        when(orikaMapperFacade.map(any(User.class), any())).thenReturn(new UpdateUserResponse());
        assertNotNull(userService.updateUserById(new UpdateUserRequest(), ""));

    }

}
