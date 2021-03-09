package com.agilethought.atsceapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.agilethought.atsceapi.exception.NotFoundException;
import com.agilethought.atsceapi.exception.UnauthorizedException;
import com.agilethought.atsceapi.model.LoginData;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.repository.UserRepository;
import com.agilethought.atsceapi.validator.LoginDataValidator;
import com.agilethought.atsceapi.validator.ValidatorUserService;

import lombok.extern.slf4j.Slf4j;
import com.agilethought.atsceapi.domain.NewUserRequest;
import com.agilethought.atsceapi.domain.NewUserResponse;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User loginMethod(LoginData loginData) {
		
		ValidatorUserService loginDataValidator = new LoginDataValidator();
		loginDataValidator.validateEmail(loginData.getEmail());
		loginDataValidator.validatePassword(loginData.getPassword());
		List<User> users = userRepository.findUsersByEmail(loginData.getEmail(), loginData.getPassword());
		if (!users.isEmpty()) {
			log.info("Get user from Database " + users.get(0));
			User user = users.get(0);
			loginDataValidator.validateStatus(user);
			return user;
		}
		throw new UnauthorizedException("Unathorized");
	}

	@Override
	public List<User> getAllUsers() {
		List<User> usersList = userRepository.findAll();
		return usersList;
	}

	@Override
	public User getUserById(String id) {
		Optional<User> userFound = userRepository.findById(id);
		if (userFound.isPresent())
			return userFound.get();
		else
			throw new NotFoundException("User Not Found with id: " + id);
	}

	@Override
	public NewUserResponse createUser(NewUserRequest request) {
		NewUserResponse response = new NewUserResponse();
		User user = new User();

		user.setType(request.getType());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setStatus(request.getStatus());
		User savedUsers = userRepository.save(user);
		response.setId(savedUsers.getId());

		return response;
	}
}