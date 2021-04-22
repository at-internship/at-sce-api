package com.agilethought.atsceapi.validator.user;

import com.agilethought.atsceapi.exception.BadRequestException;
import com.agilethought.atsceapi.exception.NotFoundException;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UpdateUserValidatorTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDataValidator userDataValidator;

    @InjectMocks
    private UpdateUserValidator updateUserValidator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUpdateUserByIdWithMissingId() {

        User mockUpdateUser = new User();

        Exception thrownException = assertThrows(
                BadRequestException.class,
                () -> {
                    updateUserValidator.validate(mockUpdateUser);
                }
        );
        assertEquals(
                "Required field Id is missing.",
                thrownException.getMessage()
        );

    }

    @Test
    public void testUpdateUserByIdWithIdNotFound() {

        User mockUpdateUser = new User();
        mockUpdateUser.setId("mockedId");

        when(userRepository.findById(anyString()))
                .thenReturn(Optional.empty());
        Exception thrownException = assertThrows(
                NotFoundException.class,
                () -> {
                    updateUserValidator.validate(mockUpdateUser);
                }
        );
        assertEquals(
                "User was not found with the given id: mockedId",
                thrownException.getMessage()
        );

    }

    @Test
    public void testUpdateUserByIdWithEmailAlreadyTaken() {

        // A user with email "current_email@example.com" tries
        // to update its email to "my_email@example.com", but
        // it's already taken by another user.
        User mockUpdateRequest = new User();
        mockUpdateRequest.setId("mockedUserToUpdateId");
        mockUpdateRequest.setEmail("my_email@example.com");

        User mockOwnerOfEmail = new User();
        mockOwnerOfEmail.setId("mockedEmailOwnerId");
        mockOwnerOfEmail.setEmail("my_email@example.com");

        User mockUserToUpdate = new User();
        mockUserToUpdate.setId("mockedUserToUpdateId");
        mockUserToUpdate.setEmail("current_email@example.com");

        when(userRepository.findById("mockedUserToUpdateId"))
                .thenReturn(Optional.of(mockUserToUpdate));
        doNothing().when(userDataValidator).validate(any());
        when(userRepository.existsByEmail(anyString())).thenReturn(true);
        Exception thrownException = assertThrows(
                BadRequestException.class,
                () -> {
                    updateUserValidator.validate(mockUpdateRequest);
                }
        );
        assertEquals(
                "Email my_email@example.com is already in use, try with another one or login.",
                thrownException.getMessage()
        );

    }

    @Test
    public void testUpdateUserByIdSuccessfullyWithNewEmail() {

        User mockUpdateUser = new User();
        mockUpdateUser.setId("1234");
        mockUpdateUser.setEmail("new_unique_email@example.com");

        when(userRepository.findById(anyString()))
                .thenReturn(Optional.of(mockUpdateUser));
        doNothing().when(userDataValidator).validate(any());
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        assertDoesNotThrow(
                () -> {
                    updateUserValidator.validate(mockUpdateUser);
                }
        );

    }

    @Test
    public void testUpdateUserByIdWithSameEmail() {

        User mockUpdateUser = new User();
        mockUpdateUser.setId("1234");
        mockUpdateUser.setEmail("my_same_email@example.com");

        when(userRepository.findById(anyString()))
                .thenReturn(Optional.of(mockUpdateUser));
        doNothing().when(userDataValidator).validate(any());
        when(userRepository.existsByEmail(anyString())).thenReturn(true);
        assertDoesNotThrow(
                () -> {
                    updateUserValidator.validate(mockUpdateUser);
                }
        );

    }

}
