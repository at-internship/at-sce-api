package com.agilethought.atsceapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilethought.atsceapi.dto.LoginData;
import com.agilethought.atsceapi.dto.NewUserRequest;
import com.agilethought.atsceapi.dto.NewUserResponse;
import com.agilethought.atsceapi.dto.UserDTO;
import com.agilethought.atsceapi.exception.NotFoundException;
import com.agilethought.atsceapi.exception.UnauthorizedException;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.repository.UserRepository;
import com.agilethought.atsceapi.validator.LoginDataValidator;
import com.agilethought.atsceapi.validator.ValidatorUserService;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MapperFacade orikaMapperFacade;
	
	@Override
	public UserDTO loginMethod(LoginData loginData) {
		ValidatorUserService loginDataValidator = new LoginDataValidator();
		loginDataValidator.validateEmail(loginData.getEmail());
		loginDataValidator.validatePassword(loginData.getPassword());
		List<User> users = userRepository.findUsersByEmail(loginData.getEmail(), loginData.getPassword());
		if (!users.isEmpty()) {
			log.info("Get user from Database " + users.get(0));
			User user = users.get(0);
			loginDataValidator.validateStatus(user);
			return orikaMapperFacade.map(user, UserDTO.class);
		}
		throw new UnauthorizedException("Unathorized");
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> usersList = userRepository.findAll();
		return orikaMapperFacade.mapAsList(usersList, UserDTO.class);
	}

	@Override
	public UserDTO getUserById(String id) {
		Optional<User> userFound = userRepository.findById(id);
		if (userFound.isPresent())
			return orikaMapperFacade.map(userFound.get(), UserDTO.class);
		else
			throw new NotFoundException("User Not Found with id: " + id);
	}

	@Override
	public NewUserResponse createUser(NewUserRequest request) {
		User user = orikaMapperFacade.map(request, User.class);
		User savedUsers = userRepository.save(user);

		return orikaMapperFacade.map(savedUsers, NewUserResponse.class);
	}
}