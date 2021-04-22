package com.agilethought.atsceapi.validator.user;

import com.agilethought.atsceapi.exception.BadRequestException;
import static com.agilethought.atsceapi.exception.ErrorMessage.ALREADY_EXISTING_EMAIL;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewUserValidator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDataValidator userDataValidator;

    public void validate(User user) {

        userDataValidator.validate(user);
        validateUniqueEmail(user.getEmail());

    }

    private void validateUniqueEmail(String email) {

        if (userRepository.existsByEmail(email))
            throw new BadRequestException(
                    String.format(ALREADY_EXISTING_EMAIL, email)
            );

    }

}
