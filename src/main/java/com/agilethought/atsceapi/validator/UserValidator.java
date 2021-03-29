package com.agilethought.atsceapi.validator;

import com.agilethought.atsceapi.exception.BadRequestException;
import static com.agilethought.atsceapi.exception.ErrorMessage.*;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidator implements Validator<User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDataValidationUtils userDataValidationUtils;

    @Override
    public void validate(User user) {
       userDataValidationUtils.validateUserData(user.getType(),user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(),user.getStatus());
        validateEmailInTheDataBase(user.getEmail());
    }

    private void validateEmailInTheDataBase(String email) {
        if(userRepository.existsByEmail(email)) {
            throw new BadRequestException("The *EMAIL*" + " " + BAD_REQUEST_MESSAGE_EMAIL_IN_DB);
        }
    }
}
