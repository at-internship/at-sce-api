package com.agilethought.atsceapi.validator;

import com.agilethought.atsceapi.exception.BadRequestException;
import static com.agilethought.atsceapi.exception.ErrorMessage.badRequestMessage;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.agilethought.atsceapi.validator.ValidationUtils.*;

@Service
@Slf4j
public class UserValidator implements Validator<User> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void validate(User user) {
        validateUserTypeField(user.getType());
        validateUserFirstName(user.getFirstName());
        validateUserLastName(user.getLastName());
        validateEmailFormat(user.getEmail());
        validatePasswordFormat(user.getPassword());
        validateEmailInTheDataBase(user.getEmail());
        user.setFirstName(user.getFirstName().toUpperCase());
        user.setLastName(user.getLastName().toUpperCase());
    }

    private void validateUserTypeField(int type) {
        if(type < 1 || type > 2) {
            log.info("fail type");
            throw new BadRequestException(badRequestMessage + "*type* because type needs to be 1 or 2");
        }
    }

    private void validateUserFirstName(String firstName) {
        if(!isStringValid(firstName) || !isStringLowerCase(firstName)) {
            throw new BadRequestException(badRequestMessage + "*name* because you need to type it");
        }
    }

    private void validateUserLastName(String lastName) {
        if(!isStringValid(lastName) || !isStringLowerCase(lastName)) {
            throw new BadRequestException(badRequestMessage + "*lastname* because you need to type it");
        }
    }

    private void validateEmailFormat(String email) {
        if(!isStringValid(email) || !isStringLowerCase(email) || !isValidEmail(email)) {
            throw new BadRequestException(badRequestMessage + "*email* because it doesn't have the correct format");
        }
    }

    private void validatePasswordFormat(String password) {
        if(!isStringValid(password) || !isStringLowerCase(password) || !isValidatePassword(password)) {
            throw new BadRequestException(badRequestMessage + "*password* because it doesn't have the correct format");
        }
    }

    private void validateEmailInTheDataBase(String email) {
        if(userRepository.existsByEmail(email)) {
            throw new BadRequestException(badRequestMessage + "*email* because it already exist in the Database");
        }
    }
}
