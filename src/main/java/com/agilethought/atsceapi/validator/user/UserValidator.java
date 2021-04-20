package com.agilethought.atsceapi.validator.user;

import static com.agilethought.atsceapi.exception.ErrorMessage.*;
import static com.agilethought.atsceapi.validator.user.ValidationUtils.*;

import com.agilethought.atsceapi.repository.UserRepository;
import com.agilethought.atsceapi.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilethought.atsceapi.exception.BadRequestException;
import com.agilethought.atsceapi.model.User;

import java.util.Optional;

@Service
public class UserValidator implements Validator<User> {

    @Autowired
    private UserRepository userRepository;

    private static final String TYPE = "Type";
    private static final String CORRECT_FORMAT_TYPE = "Number 1 for admin or number 2 " +
            "for normal user";

    private static final String FIRST_NAME = "First name";

    private static final String LAST_NAME = "Last name";

    private static final String CORRECT_FORMAT_EMAIL = "an_accepted-email.example@domain.com.mx";

    private static final String CORRECT_FORMAT_PASSWORD = "10 characters minimum with " +
            "at least one lowercase letter, one uppercase letter, and one number.";

    private static final String STATUS = "Status of user";
    private static final String CORRECT_FORMAT_STATUS = "Number 0 for unavailable or " +
            "number 1 for available";

    @Override
    public void validate(User user) {

        validateType(user.getType());
        validateFirstName(user.getFirstName());
        validateLastName(user.getLastName());
        validateEmailFormat(user.getEmail());
        // If there is no value in the user's id,
        // it's a new user being created.
        if (user.getId() == null)
            validateUniqueEmailForNewUser(user.getEmail());
        else
            validateUniqueEmailForExistingUser(user.getEmail(), user.getId());
        validatePassword(user.getPassword());
        validateStatus(user.getStatus());

    }

    private void validateType(Integer type) {

        if (type == null) {
            throw new BadRequestException(
                    String.format(MISSING_REQUIRED_INPUT, TYPE)
            );
        }
        if (type < 1 || type > 2) {
            throw new BadRequestException(
                    String.format(INVALID_INPUT, TYPE, CORRECT_FORMAT_TYPE)
            );
        }

    }

    private void validateFirstName(String firstName) {

        if (!isValidString(firstName)) {
            throw new BadRequestException(
                    String.format(MISSING_REQUIRED_INPUT, FIRST_NAME)
            );
        }

    }

    private void validateLastName(String lastName) {

        if (!isValidString(lastName)) {
            throw new BadRequestException(
                    String.format(MISSING_REQUIRED_INPUT, LAST_NAME)
            );
        }

    }

    private void validateEmailFormat(String email) {

        if (!isValidString(email)) {
            throw new BadRequestException(
                    String.format(MISSING_REQUIRED_INPUT, EMAIL)
            );
        }
        if (!isValidEmail(email)) {
            throw new BadRequestException(
                    String.format(INVALID_INPUT, EMAIL, CORRECT_FORMAT_EMAIL)
            );
        }

    }

    private void validateUniqueEmailForNewUser(String email) {

        if (userRepository.existsByEmail(email))
            throw new BadRequestException(
                    String.format(ALREADY_EXISTING_EMAIL, email)
            );

    }

    private void validateUniqueEmailForExistingUser(String email, String id) {

        Optional<User> userToBeUpdated = userRepository.findById(id);
        if (userRepository.existsByEmail(email))
            // If the email in the update request exists, it must be equal
            // than the one inside the user to be updated. Otherwise, the
            // email in the request already belongs to somebody else.
            if (!email.equals(userToBeUpdated.get().getEmail()))
                throw new BadRequestException(
                        String.format(ALREADY_EXISTING_EMAIL, email)
                );

    }

    private void validatePassword(String password) {

        if (!isValidString(password)) {
            throw new BadRequestException(
                    String.format(MISSING_REQUIRED_INPUT, PASSWORD)
            );
        }
        if (!isValidPassword(password)) {
            throw new BadRequestException(
                    String.format(INVALID_INPUT, PASSWORD, CORRECT_FORMAT_PASSWORD)
            );
        }

    }

    private void validateStatus(Integer status) {

        if (status == null) {
            throw new BadRequestException(
                    String.format(MISSING_REQUIRED_INPUT, STATUS)
            );
        }
        if( status < 0 || status > 1) {
                throw new BadRequestException(
                        String.format(INVALID_INPUT, STATUS, CORRECT_FORMAT_STATUS)
                );
        }

    }

}
