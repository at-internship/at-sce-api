package com.agilethought.atsceapi.validator;

import com.agilethought.atsceapi.exception.BadRequestException;
import org.springframework.stereotype.Service;

import static com.agilethought.atsceapi.exception.ErrorMessage.*;
import static com.agilethought.atsceapi.exception.ErrorMessage.BAD_REQUEST_MESSAGE_TYPE;
import static com.agilethought.atsceapi.validator.ValidationUtils.*;

@Service
public class UserDataValidationUtils {
    public void validateUserData(Integer type, String firstName, String lastName, String email, String password, Integer status){
        validateUserTypeField(type);
        validateUserFirstName(firstName);
        validateUserLastName(lastName);
        validateEmailFormat(email);
        validatePasswordFormat(password);
        validateUserStatus(status);

    }

    private void validateUserStatus(Integer status) {
        if(status != null) {
            if( status < 0 || status > 1) {
                throw new BadRequestException(BAD_REQUEST_MESSAGE_STATUS);
            }
        }
    }

    private void validatePasswordFormat(String password) {

        if(!isStringValid(password) || !isStringLowerCase(password) || !isValidPassword(password)) {
            throw new BadRequestException(BAD_REQUEST_MESSAGE + "*PASSWORD*" + " " + BAD_REQUEST_MESSAGE_PASSWORD);
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

    private void validateUserFirstName(String firstName) {
        if(!isStringValid(firstName)) {
            throw new BadRequestException(BAD_REQUEST_MESSAGE + "*NAME*" + " " + BAD_REQUEST_MESSAGE_FIRST_NAME_LASTNAME);
        }
    }

    private void validateUserTypeField(Integer type) {
        if(type < 1 || type > 2) {
            throw new BadRequestException(BAD_REQUEST_MESSAGE + "*TYPE*" + " " + BAD_REQUEST_MESSAGE_TYPE);
        }
    }

}
