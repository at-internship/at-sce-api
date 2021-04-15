package com.agilethought.atsceapi.validator;

import com.agilethought.atsceapi.dto.user.LoginRequest;
import com.agilethought.atsceapi.exception.BadRequestException;
import com.agilethought.atsceapi.exception.UnauthorizedException;
import com.agilethought.atsceapi.validator.user.LoginValidator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class LoginValidatorTest {

    @Test
    public void validateEmailTest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("blanca.galindo@agilethought.com");
        loginRequest.setPassword("HelloWorld123");

        Assertions.assertAll(
                () -> {
                    loginRequest.setEmail(null);
                    // check if the email is null
                    Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                        LoginValidator userValidator = new LoginValidator();
                        userValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals(
                            "Required field Email is missing.",
                            exception.getMessage()
                    );
                },
                () -> {
                    loginRequest.setEmail("");
                    Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                        LoginValidator userValidator = new LoginValidator();
                        userValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals(
                            "Required field Email is missing.",
                            exception.getMessage()
                    );
                },
                () -> {
                    loginRequest.setEmail("  ");
                    Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                        LoginValidator userValidator = new LoginValidator();
                        userValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals("Required field Email is missing.",
                            exception.getMessage()
                    );
                },
                () -> {
                    loginRequest.setEmail("owenexample.com");
                    Exception exception = Assertions.assertThrows(UnauthorizedException.class, () -> {
                        LoginValidator userValidator = new LoginValidator();
                        userValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals(
                            "Invalid input on field Email. Correct format is: an_accepted-email.example@domain.com.mx",
                            exception.getMessage()
                    );
                },
                () -> {
                    loginRequest.setEmail("owen@example");
                    Exception exception = Assertions.assertThrows(UnauthorizedException.class, () -> {
                        LoginValidator userValidator = new LoginValidator();
                        userValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals(
                            "Invalid input on field Email. Correct format is: an_accepted-email.example@domain.com.mx",
                            exception.getMessage()
                    );
                }
        );
    }
}
