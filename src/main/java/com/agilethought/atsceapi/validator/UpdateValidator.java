package com.agilethought.atsceapi.validator;

import com.agilethought.atsceapi.dto.UpdateUserRequest;
import com.agilethought.atsceapi.exception.BadRequestException;
import com.agilethought.atsceapi.exception.NotFoundException;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.agilethought.atsceapi.exception.ErrorMessage.*;
import static com.agilethought.atsceapi.validator.ValidationUtils.*;

@Service
public class UpdateValidator implements Validator<UpdateUserRequest> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void validate(UpdateUserRequest userUp) {
        validateUserId(userUp.getId());
        validateUserTypeField(userUp.getType());
        validateUserFirstName(userUp.getFirstName());
        validateUserLastName(userUp.getLastName());
        validateEmailFormat(userUp.getEmail());
        validatePasswordFormat(userUp.getPassword());
        validateUserStatus(userUp.getStatus());
        validateEmailInTheDataBase(userUp.getEmail(), userUp.getId());
    }

    private void validateUserId(String id) {
        if(!userRepository.existsById(id)) {
            throw new NotFoundException("User Not Found with id: " + id);
        }
    }

    private void validateEmailInTheDataBase(String email, String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent() && !user.get().getEmail().equals(email) && userRepository.existsByEmail(email)){
            throw new BadRequestException("The *EMAIL*" + " " + BAD_REQUEST_MESSAGE_EMAIL_IN_DB);
        }
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

    private void validateUserTypeField(int type) {
        if(type < 1 || type > 2) {
            throw new BadRequestException(BAD_REQUEST_MESSAGE + "*TYPE*" + " " + BAD_REQUEST_MESSAGE_TYPE);
        }
    }

}
