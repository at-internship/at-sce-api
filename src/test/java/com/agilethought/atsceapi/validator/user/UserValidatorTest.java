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
    
}
