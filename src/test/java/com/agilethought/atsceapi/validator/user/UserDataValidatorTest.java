package com.agilethought.atsceapi.validator.user;

import static com.agilethought.atsceapi.exception.ErrorMessage.VALIDATION_ERROR;
import com.agilethought.atsceapi.exception.BadRequestException;
import com.agilethought.atsceapi.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class UserDataValidatorTest {

    private UserDataValidator userDataValidator;

    @Before
    public void setup() {
        userDataValidator = new UserDataValidator();
    }

    @Test
    public void itShouldThrowErrorMessagesInTypeField() {
        User user = new User();
        user.setFirstName("Owen");
        user.setLastName("Ramirez");
        user.setEmail("owen@example.com");
        user.setPassword("HelloWorld123");
        user.setStatus(0);

        Assertions.assertAll(
            () -> {
                // Check the message when the type field is null
                user.setType(null);
                Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
                        errorMessageException.getMessage()
                );
            },
            () -> {
                // Check the message when the type field is 0
                user.setType(0);
                Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
                        errorMessageException.getMessage()
                );
            },
            () -> {
                // Check the message when the type field is -1
                user.setType(-1);
                Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
                        errorMessageException.getMessage()
                );
            },
            () -> {
                // Check the message when the type field is 3
                user.setType(3);
                Exception errorMessageException = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
                        errorMessageException.getMessage()
                );
            }
        );
    }

    @Test
    public void itShouldThrowErrorMessagesInFirstNameField() {
        User user = new User();
        user.setType(2);
        user.setLastName("Ramirez");
        user.setEmail("owen@example.com");
        user.setPassword("HelloWorld123");
        user.setStatus(0);

        Assertions.assertAll(
            () -> {
                user.setFirstName(null);
                Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
                		exception.getMessage()
                );
            },
            () -> {
                user.setFirstName("");
                Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
                		exception.getMessage()
                );
            },
            () -> {
                user.setFirstName("   ");
                Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
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
        user.setEmail("owen@example.com");
        user.setPassword("HelloWorld123");
        user.setStatus(0);

        Assertions.assertAll(
                () -> {
                    user.setLastName(null);
                    Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                        userDataValidator.validate(user);
                    });
                    Assertions.assertEquals(
                    		VALIDATION_ERROR,
                    		exception.getMessage()
                    );
                },
                () -> {
                    user.setLastName("");
                    Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                        userDataValidator.validate(user);
                    });
                    Assertions.assertEquals(
                    		VALIDATION_ERROR,
                    		exception.getMessage()
                    );
                },
                () -> {
                    user.setLastName("   ");
                    Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                        userDataValidator.validate(user);
                    });
                    Assertions.assertEquals(
                    		VALIDATION_ERROR,
                    		exception.getMessage()
                    );
                }
        );
    }

    @Test
    public void itShouldThrowErrorMessagesInEmailFieldFormat() {
        User user = new User();
        user.setType(2);
        user.setFirstName("Owen");
        user.setLastName("Ramirez");
        user.setPassword("HelloWorld123");
        user.setStatus(0);

        Assertions.assertAll(
            () -> {
                user.setEmail(null);
                // check if the email is null
                Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
                		exception.getMessage()
                );
            },
            () -> {
                user.setEmail("");
                Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
                		exception.getMessage()
                );
            },
            () -> {
                user.setEmail("  ");
                Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
                		exception.getMessage()
                );
            },
            () -> {
                user.setEmail("owenexample.com");
                Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
                		exception.getMessage()
                );
            },
            () -> {
                user.setEmail("owen@example");
                Exception exception = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
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
        user.setStatus(0);

        Assertions.assertAll(
            () -> {
                user.setPassword(null);
                Exception errorMessagePassword = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(VALIDATION_ERROR, errorMessagePassword.getMessage());
            },
            () -> {
                user.setPassword("");
                Exception errorMessagePassword = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(VALIDATION_ERROR, errorMessagePassword.getMessage());
            },
            () -> {
                user.setPassword("  ");
                Exception errorMessagePassword = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(VALIDATION_ERROR, errorMessagePassword.getMessage());
            },
            () -> {
                user.setPassword("hiWorld");
                Exception errorMessagePassword = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
                        errorMessagePassword.getMessage()
                );
            },
            () -> {
                user.setPassword("helloworld");
                Exception errorMessagePassword = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
                        errorMessagePassword.getMessage()
                );
            },
            () -> {
                user.setPassword("helloWorld");
                Exception errorMessagePassword = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
                        errorMessagePassword.getMessage()
                );
            }
        );
    }

    @Test
    public void itShouldThrowErrorMessagesInStatusField() {
        User user = new User();
        user.setType(2);
        user.setFirstName("Owen");
        user.setLastName("Ramirez");
        user.setEmail("owen@example.com");
        user.setPassword("HelloWorld123");

        Assertions.assertAll(
            () -> {
                user.setStatus(null);
                Exception errorMessageStatus = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
                        errorMessageStatus.getMessage()
                );
            },
            () -> {
                user.setStatus(-1);
                Exception errorMessageStatus = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
                        errorMessageStatus.getMessage()
                );
            },
            () -> {
                user.setStatus(2);
                Exception errorMessageStatus = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
                        errorMessageStatus.getMessage()
                );
            },
            () -> {
                user.setStatus(10);
                Exception errorMessageStatus = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
                        errorMessageStatus.getMessage()
                );
            },
            () -> {
                user.setStatus(-10);
                Exception errorMessageStatus = Assertions.assertThrows(BadRequestException.class, () -> {
                    userDataValidator.validate(user);
                });
                Assertions.assertEquals(
                		VALIDATION_ERROR,
                        errorMessageStatus.getMessage()
                );
            },
            () -> {
                user.setStatus(0);
                userDataValidator.validate(user);
            }
        );
    }
}
