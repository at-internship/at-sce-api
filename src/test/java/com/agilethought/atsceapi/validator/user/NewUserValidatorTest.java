package com.agilethought.atsceapi.validator.user;

import com.agilethought.atsceapi.exception.BadRequestException;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

public class NewUserValidatorTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDataValidator userDataValidator;

    @InjectMocks
    private NewUserValidator newUserValidator;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUserWithEmailAlreadyTaken() {

        User mockCreateUser = new User();
        mockCreateUser.setEmail("email_already_taken@example.com");

        doNothing().when(userDataValidator).validate(any());
        when(userRepository.existsByEmail(anyString())).thenReturn(true);
        Exception thrownException = assertThrows(
                BadRequestException.class,
                () -> {
                    newUserValidator.validate(mockCreateUser);
                }
        );
        assertEquals(
                String.format(
                        "email %s is already in use, try with another one or login.",
                        mockCreateUser.getEmail()
                ),
                thrownException.getMessage()
        );

    }

    @Test
    public void testCreateUserWithNewEmail() {

        User mockCreateUser = new User();
        mockCreateUser.setEmail("new_unique_email@example.com");

        doNothing().when(userDataValidator).validate(any());
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        assertDoesNotThrow(
                () -> {
                    newUserValidator.validate(mockCreateUser);
                }
        );

    }

}
