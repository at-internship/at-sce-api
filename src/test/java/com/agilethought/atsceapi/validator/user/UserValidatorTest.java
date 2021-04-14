package com.agilethought.atsceapi.validator.user;

import com.agilethought.atsceapi.exception.BadRequestException;
import com.agilethought.atsceapi.model.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class UserValidatorTest {

    @Test
    public void itShouldThrowErrorMessagesInTypeField() {
        User user = new User();
        user.setType(null);
        user.setFirstName("Owen");
        user.setLastName("Ramirez");
        user.setEmail("owen@example.com");
        user.setPassword("HelloWorld123");
        user.setStatus(0);

        Assertions.assertAll(
            () -> {
                // Check the message when the type field is null
                Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                Assertions.assertEquals(
                        "Required field Type is missing.",
                        errorMessageException.getMessage()
                );
            },
            () -> {
                // Check the message when the type field is 0
                user.setType(0);
                Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                Assertions.assertEquals(
                        "Invalid input on field Type. Correct format is: Number 1 for admin or number 2 for normal user",
                        errorMessageException.getMessage()
                );
            },
            () -> {
                // Check the message when the type field is -1
                user.setType(-1);
                Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                Assertions.assertEquals(
                        "Invalid input on field Type. Correct format is: Number 1 for admin or number 2 for normal user",
                        errorMessageException.getMessage()
                );
            },
            () -> {
                // Check the message when the type field is 3
                user.setType(3);
                Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                Assertions.assertEquals(
                        "Invalid input on field Type. Correct format is: Number 1 for admin or number 2 for normal user",
                        errorMessageException.getMessage()
                );
            }
        );
    }

    @Test
    public void itShouldThrowErrorMessagesInFirstNameField() {
        User user = new User();
        user.setType(2);
        user.setFirstName(null);
        user.setLastName("Ramirez");
        user.setEmail("owen@example.com");
        user.setPassword("HelloWorld123");
        user.setStatus(0);

        Assertions.assertAll(
            () -> {
                Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                Assertions.assertEquals(
                        "Required field First name is missing.",
                        exception.getMessage()
                );
            },
            () -> {
                user.setFirstName("");
                Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                Assertions.assertEquals(
                        "Required field First name is missing.",
                        exception.getMessage()
                );
            },
            () -> {
                user.setFirstName("   ");
                Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                Assertions.assertEquals(
                        "Required field First name is missing.",
                        exception.getMessage()
                );
            }
        );
    }

    @Test
    public void itShouldThrowErrorMessagesInLastNameField() {
        User user = new User();
        user.setType(2);
        user.setFirstName("Owen");
        user.setLastName(null);
        user.setEmail("owen@example.com");
        user.setPassword("HelloWorld123");
        user.setStatus(0);

        Assertions.assertAll(
                () -> {
                    Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                        UserValidator userValidator = new UserValidator();
                        userValidator.validate(user);
                    });
                    Assertions.assertEquals(
                            "Required field Last name is missing.",
                            exception.getMessage()
                    );
                },
                () -> {
                    user.setLastName("");
                    Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                        UserValidator userValidator = new UserValidator();
                        userValidator.validate(user);
                    });
                    Assertions.assertEquals(
                            "Required field Last name is missing.",
                            exception.getMessage()
                    );
                },
                () -> {
                    user.setLastName("   ");
                    Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                        UserValidator userValidator = new UserValidator();
                        userValidator.validate(user);
                    });
                    Assertions.assertEquals(
                            "Required field Last name is missing.",
                            exception.getMessage()
                    );
                }
        );
    }

    @Test
    public void itShouldThrowErrorMessagesInEmailField() {
        User user = new User();
        user.setType(2);
        user.setFirstName("Owen");
        user.setLastName("Ramirez");
        user.setEmail(null);
        user.setPassword("HelloWorld123");
        user.setStatus(0);

        Assertions.assertAll(
            () -> {
                // check if the email is null
                Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                Assertions.assertEquals(
                        "Required field Email is missing.",
                        exception.getMessage()
                );
            },
            () -> {
                user.setEmail("");
                Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                Assertions.assertEquals(
                        "Required field Email is missing.",
                        exception.getMessage()
                );
            },
            () -> {
                user.setEmail("  ");
                Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                Assertions.assertEquals("Required field Email is missing.",
                        exception.getMessage()
                );
            },
            () -> {
                user.setEmail("owenexample.com");
                Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                Assertions.assertEquals(
                        "Invalid input on field Email. Correct format is: an_accepted-email.example@domain.com.mx",
                        exception.getMessage()
                );
            },
            () -> {
                user.setEmail("owen@example");
                Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                Assertions.assertEquals(
                        "Invalid input on field Email. Correct format is: an_accepted-email.example@domain.com.mx",
                        exception.getMessage()
                );
            }
        );
    }

    @Test
    public void itShouldThrowErrorMessagesPasswordField() {
        User user = new User();
        user.setType(2);
        user.setFirstName("Owen");
        user.setLastName("Ramirez");
        user.setEmail("owen@example.com");
        user.setPassword(null);
        user.setStatus(0);

        Assertions.assertAll(
            () -> {
                Exception errorMessagePassword = Assertions.assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                Assertions.assertEquals("Required field Password is missing.", errorMessagePassword.getMessage());
            },
            () -> {
                user.setPassword("");
                Exception errorMessagePassword = Assertions.assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                Assertions.assertEquals("Required field Password is missing.", errorMessagePassword.getMessage());
            },
            () -> {
                user.setPassword("  ");
                Exception errorMessagePassword = Assertions.assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                Assertions.assertEquals("Required field Password is missing.", errorMessagePassword.getMessage());
            },
            () -> {
                user.setPassword("hiWorld");
                Exception errorMessagePassword = Assertions.assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                Assertions.assertEquals(
                    "Invalid input on field Password. Correct format is: 10 characters minimum with "
                        + "at least one lowercase letter, one uppercase letter, and one number.",
                        errorMessagePassword.getMessage()
                );
            },
            () -> {
                user.setPassword("helloworld");
                Exception errorMessagePassword = Assertions.assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                Assertions.assertEquals(
                        "Invalid input on field Password. Correct format is: 10 characters minimum with "
                                + "at least one lowercase letter, one uppercase letter, and one number.",
                        errorMessagePassword.getMessage()
                );
            },
            () -> {
                user.setPassword("helloWorld");
                Exception errorMessagePassword = Assertions.assertThrows(BadRequestException.class, () -> {
                    UserValidator userValidator = new UserValidator();
                    userValidator.validate(user);
                });
                Assertions.assertEquals(
                        "Invalid input on field Password. Correct format is: 10 characters minimum with "
                                + "at least one lowercase letter, one uppercase letter, and one number.",
                        errorMessagePassword.getMessage()
                );
            }
        );
    }
}
