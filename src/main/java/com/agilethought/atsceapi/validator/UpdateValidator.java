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

@Service
public class UpdateValidator implements Validator<UpdateUserRequest> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDataValidationUtils userDataValidationUtils;

    @Override
    public void validate(UpdateUserRequest userUp) {
        userDataValidationUtils.validateUserData(userUp.getType(),userUp.getFirstName(),userUp.getLastName(),userUp.getEmail(),userUp.getPassword(),userUp.getStatus());
        validateUserId(userUp.getId());
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

}
