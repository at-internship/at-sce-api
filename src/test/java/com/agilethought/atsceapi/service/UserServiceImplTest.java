package com.agilethought.atsceapi.service;

import static com.agilethought.atsceapi.exception.ErrorMessage.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.agilethought.atsceapi.dto.user.*;
import com.agilethought.atsceapi.exception.BadRequestException;
import com.agilethought.atsceapi.exception.NotFoundException;
import com.agilethought.atsceapi.exception.UnauthorizedException;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.service.implementation.UserServiceImpl;
import com.agilethought.atsceapi.validator.Validator;
import com.agilethought.atsceapi.validator.user.UserValidator;
import com.agilethought.atsceapi.repository.UserRepository;

import ma.glasnost.orika.MapperFacade;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MapperFacade orikaMapperFacade;

    @Mock
    private Validator<LoginRequest> loginValidator;

    @Mock
    private UserValidator userValidator;

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
    public void testLoginUser_ValidCredentials_Available() {

        // Given
        LoginRequest mockLoginRequest = new LoginRequest();
        mockLoginRequest.setEmail("");
        mockLoginRequest.setPassword("");

        List<User> mockUsersList = new ArrayList<>();
        mockUsersList.add(new User());
        mockUsersList.get(0).setStatus(1);

        // Then
        doNothing().when(loginValidator).validate(any());
        when(userRepository.findUserWithCredentials(anyString(), anyString()))
                .thenReturn(mockUsersList);
        when(orikaMapperFacade.map(any(), any())).thenReturn(new LoginResponse());
        LoginResponse testResult = userService.loginUser(mockLoginRequest);
        assertNotNull(testResult);

    }

    @Test
    public void testLoginUser_ValidCredentials_Unavailable() {

        // Given
        LoginRequest mockLoginRequest = new LoginRequest();
        mockLoginRequest.setEmail("");
        mockLoginRequest.setPassword("");

        List<User> mockUsersList = new ArrayList<>();
        mockUsersList.add(new User());
        mockUsersList.get(0).setStatus(0);

        // Then
        doNothing().when(loginValidator).validate(any());
        when(userRepository.findUserWithCredentials(anyString(), anyString()))
                .thenReturn(mockUsersList);
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
    public void testLoginUser_InvalidCredentials() {

        // Given
        LoginRequest mockLoginRequest = new LoginRequest();
        mockLoginRequest.setEmail("");
        mockLoginRequest.setPassword("");

        // Then
        doNothing().when(loginValidator).validate(any());
        when(userRepository.findUserWithCredentials(anyString(), anyString()))
                .thenReturn(new ArrayList<>());
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
    public void testGetUserById_Found() {

        when(userRepository.findById(anyString())).thenReturn(Optional.of(new User()));
        when(orikaMapperFacade.map(any(), any())).thenReturn(new UserDTO());
        UserDTO testResult = userService.getUserById(anyString());
        assertNotNull(testResult);

    }

    @Test
    public void testGetUserById_NotFound() {

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
    public void testDeleteUserById_Found() {

        when(userRepository.findById(anyString())).thenReturn(Optional.of(new User()));
        doNothing().when(userRepository).deleteById(anyString());
        userService.deleteUserById(anyString());
        verify(userRepository).deleteById(anyString());

    }

    @Test
    public void testDeleteUserById_NotFound() {

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
    public void testCreateUser_Successful() {

        // Given
        User mockNewUser = new User();
        mockNewUser.setFirstName("");
        mockNewUser.setLastName("");
        mockNewUser.setEmail("");

        // Then
        when(orikaMapperFacade.map(any(NewUserRequest.class), any()))
                .thenReturn(mockNewUser);
        doNothing().when(userValidator).validate(any());
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any())).thenReturn(new User());
        when(orikaMapperFacade.map(any(User.class), any())).thenReturn(new NewUserResponse());
        assertNotNull(userService.createUser(new NewUserRequest()));

    }

    @Test
    public void testCreateUser_AlreadyExistingEmail() {

        // Given
        User mockNewUser = new User();
        mockNewUser.setFirstName("");
        mockNewUser.setLastName("");
        mockNewUser.setEmail("");

        // Then
        when(orikaMapperFacade.map(any(), any())).thenReturn(mockNewUser);
        doNothing().when(userValidator).validate(any());
        when(userRepository.existsByEmail(anyString())).thenReturn(true);
        BadRequestException thrownException = assertThrows(
                BadRequestException.class,
                () -> userService.createUser(new NewUserRequest())
        );
        assertEquals(
                String.format(ALREADY_EXISTING_EMAIL, ""),
                thrownException.getMessage()
        );

    }

    @Test
    public void testUpdateUser_Successful() {

        // Given
        User mockUpdateUser = new User();
        mockUpdateUser.setFirstName("");
        mockUpdateUser.setLastName("");
        mockUpdateUser.setEmail("");

        // Then
        when(userRepository.findById(anyString())).thenReturn(Optional.of(new User()));
        when(orikaMapperFacade.map(any(UpdateUserRequest.class), any()))
                .thenReturn(mockUpdateUser);
        doNothing().when(userValidator).validate(any());
        when(userRepository.save(any())).thenReturn(new User());
        when(orikaMapperFacade.map(any(User.class), any())).thenReturn(new UpdateUserResponse());
        assertNotNull(userService.updateUser(new UpdateUserRequest(), anyString()));

    }

    @Test
    public void testUpdateUser_NotFound() {

        when(userRepository.findById(anyString())).thenReturn(Optional.empty());
        NotFoundException thrownException = assertThrows(
                NotFoundException.class,
                () -> userService.updateUser(new UpdateUserRequest(), anyString())
        );
        assertEquals(
                String.format(NOT_FOUND_RESOURCE, USER, ""),
                thrownException.getMessage()
        );
    }

}
