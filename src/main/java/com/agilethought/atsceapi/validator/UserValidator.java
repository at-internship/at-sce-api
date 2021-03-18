package com.agilethought.atsceapi.validator;

import com.agilethought.atsceapi.exception.BadRequestException;
import static com.agilethought.atsceapi.exception.ErrorMessage.*;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.agilethought.atsceapi.validator.ValidationUtils.*;

@Service
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
        validateUserStatus(user.getStatus());
        validateEmailInTheDataBase(user.getEmail());
    }

    private void validateUserTypeField(int type) {
        if(type < 1 || type > 2) {
            throw new BadRequestException(BAD_REQUEST_MESSAGE + "*TYPE*" + " " + BAD_REQUEST_MESSAGE_TYPE);
        }
    }

    private void validateUserFirstName(String firstName) {
        if(!isStringValid(firstName)) {
            throw new BadRequestException(BAD_REQUEST_MESSAGE + "*NAME*" + " " + BAD_REQUEST_MESSAGE_FIRST_NAME_LASTNAME);
        }
    }

    private void validateUserLastName(String lastName) {
        if(!isStringValid(lastName)) {
            throw new BadRequestException(BAD_REQUEST_MESSAGE + "*LASTNAME*" + " " + BAD_REQUEST_MESSAGE_FIRST_NAME_LASTNAME);
        }
    }

    private void validateEmailFormat(String email) {
        if(!isStringValid(email) || !isStringLowerCase(email) || !isValidEmail(email)) {
            throw new BadRequestException(BAD_REQUEST_MESSAGE + "*EMAIL*" + " " + BAD_REQUEST_MESSAGE_EMAIL);
        }
    }

    private void validatePasswordFormat(String password) {
        if(!isStringValid(password) || !isStringLowerCase(password) || !isValidPassword(password)) {
            throw new BadRequestException(BAD_REQUEST_MESSAGE + "*PASSWORD*" + " " + BAD_REQUEST_MESSAGE_PASSWORD);
        }
    }

    private void validateUserStatus(Integer status) {
        if(status != null) {
            if( status < 0 || status > 1) {
                throw new BadRequestException(BAD_REQUEST_MESSAGE_STATUS);
            }
        }

    }

    private void validateEmailInTheDataBase(String email) {
        if(userRepository.existsByEmail(email)) {
            throw new BadRequestException("The *EMAIL*" + " " + BAD_REQUEST_MESSAGE_EMAIL_IN_DB);
        }
    }

}
