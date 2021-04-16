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
        loginRequest.setEmail("juan.perez@agilethought.com");
        loginRequest.setPassword("HelloWorld123");

        Assertions.assertAll(
                () -> {
                    loginRequest.setEmail(null);
                    // check if the email is null
                    Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                        LoginValidator loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals(
                            "Required field Email is missing.",
                            exception.getMessage()
                    );
                },
                () -> {
                    loginRequest.setEmail("");
                    Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                        LoginValidator loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals(
                            "Required field Email is missing.",
                            exception.getMessage()
                    );
                },
                () -> {
                    loginRequest.setEmail("  ");
                    Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                        LoginValidator loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals("Required field Email is missing.",
                            exception.getMessage()
                    );
                },
                () -> {
                    loginRequest.setEmail("juanperez.com");
                    Exception exception = Assertions.assertThrows(UnauthorizedException.class, () -> {
                        LoginValidator loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals(
                            "Invalid login credentials.",
                            exception.getMessage()
                    );
                },
                () -> {
                    loginRequest.setEmail("juan@example");
                    Exception exception = Assertions.assertThrows(UnauthorizedException.class, () -> {
                        LoginValidator loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals(
                            "Invalid login credentials.",
                            exception.getMessage()
                    );
                }
        );
    }

    @Test
    public void validatePasswordTest() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("owen@example.com");
        loginRequest.setPassword("Holamundo123");

        Assertions.assertAll(
                () -> {
                    loginRequest.setPassword(null);
                    Exception errorMessagePassword = Assertions.assertThrows(BadRequestException.class, () -> {
                        LoginValidator loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals("Required field Password is missing.", errorMessagePassword.getMessage());
                },
                () -> {
                    loginRequest.setPassword("");
                    Exception errorMessagePassword = Assertions.assertThrows(BadRequestException.class, () -> {
                        LoginValidator loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals("Required field Password is missing.", errorMessagePassword.getMessage());
                },
                () -> {
                    loginRequest.setPassword("  ");
                    Exception errorMessagePassword = Assertions.assertThrows(BadRequestException.class, () -> {
                        LoginValidator loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals("Required field Password is missing.", errorMessagePassword.getMessage());
                },
                () -> {
                    loginRequest.setPassword("hiWorld");
                    Exception errorMessagePassword = Assertions.assertThrows(UnauthorizedException.class, () -> {
                        LoginValidator loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals(
                            "Invalid login credentials.",
                            errorMessagePassword.getMessage()
                    );
                },
                () -> {
                    loginRequest.setPassword("helloworld");
                    Exception errorMessagePassword = Assertions.assertThrows(UnauthorizedException.class, () -> {
                        LoginValidator  loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals(
                            "Invalid login credentials.",
                            errorMessagePassword.getMessage()
                    );
                },
                () -> {
                    loginRequest.setPassword("helloWorld");
                    Exception errorMessagePassword = Assertions.assertThrows(UnauthorizedException.class, () -> {
                        LoginValidator  loginValidator = new LoginValidator();
                        loginValidator.validate(loginRequest);
                    });
                    Assertions.assertEquals(
                            "Invalid login credentials.",
                            errorMessagePassword.getMessage()
                    );
                },
                ()-> {
                    loginRequest.setPassword("Helloworld1234");
                    LoginValidator  loginValidator = new LoginValidator();
                    loginValidator.validate(loginRequest);
                }
        );
    }

}
