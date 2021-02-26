package com.agilethought.atsceapi.service;

import com.agilethought.atsceapi.exception.NotFoundException;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(String id) {
        Optional<User> userFound = userRepository.findById(id);
        if (userFound.isPresent())
            return userFound.get();
        else
            throw new NotFoundException("User Not Found with id: "+ id);
    }
}
