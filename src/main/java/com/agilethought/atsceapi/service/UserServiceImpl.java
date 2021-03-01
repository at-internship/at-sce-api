package com.agilethought.atsceapi.service;

import com.agilethought.atsceapi.exception.NotFoundException;
import com.agilethought.atsceapi.exceptions.UnauthorizedException;
import com.agilethought.atsceapi.model.LoginData;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loginMethod(LoginData loginData) {

        if (isValid(loginData.getEmail()) && isStringLowerCase(loginData.getEmail())) {
            List<User> users = userRepository.findUsersByEmail(loginData.getEmail(), loginData.getPassword());
            if (!users.isEmpty()) {
                log.info("Get user from Database " + users.get(0));
                return users.get(0);
            }
        }
        log.info("Email not valid " + loginData.getEmail());
        throw new UnauthorizedException("User not authorized");
    }

    @Override
    public User getUserById(String id) {
        Optional<User> userFound = userRepository.findById(id);
        if (userFound.isPresent())
            return userFound.get();
        else
            throw new NotFoundException("User Not Found with id: " + id);
    }

    private static boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    private static boolean isStringLowerCase(String str) {
        //convert String to char array
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            //if the character is a letter
            if (Character.isLetter(charArray[i])) {
                //if any character is not in lower case, return false
                if (!Character.isLowerCase(charArray[i]))
                    return false;
            }
        }
        return true;
    }
}