package com.agilethought.atsceapi.service;

import java.util.List;
import java.util.Optional;

import com.agilethought.atsceapi.validator.UpdateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilethought.atsceapi.dto.*;
import com.agilethought.atsceapi.validator.UserValidator;
import com.agilethought.atsceapi.exception.NotFoundException;
import com.agilethought.atsceapi.exception.UnauthorizedException;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.repository.UserRepository;
import com.agilethought.atsceapi.validator.Validator;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;

import static com.agilethought.atsceapi.validator.ValidationUtils.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MapperFacade orikaMapperFacade;

	@Autowired
	private Validator<LoginData> loginValidator;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private UpdateValidator updateValidator;

	@Override
	public LoginDataResponse loginMethod(LoginData loginData) {
		loginValidator.validate(loginData);
		List<User> users = userRepository.findUsersByEmail(loginData.getEmail(), loginData.getPassword());
		if (!users.isEmpty()) {
			log.info("Get user from Database " + users.get(0));
			User user = users.get(0);
			if (user.getStatus() == 0)
				throw new UnauthorizedException("Unauthorized");
			return orikaMapperFacade.map(user, LoginDataResponse.class);
		}
		throw new UnauthorizedException("Unauthorized");
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
	public void deleteUserById(String id) {
		Optional<User> usFound = userRepository.findById(id);
		if (!usFound.isPresent())
			throw new NotFoundException("User Not Found with id: " + id);
		else
			userRepository.deleteById(id);
	}

	@Override
	public NewUserResponse createUser(NewUserRequest request) {
		User user = orikaMapperFacade.map(request, User.class);
		userValidator.validate(user);
		setUpperCase(user);
		User savedUsers = userRepository.save(user);
		return orikaMapperFacade.map(savedUsers, NewUserResponse.class);
	}

	@Override
	public UpdateUserResponse updateUser(UpdateUserRequest request, String id) {
		request.setId(id);
		updateValidator.validate(request);
		User user = orikaMapperFacade.map(request, User.class);
		setUpperCase(user);
		User updatedUser = userRepository.save(user);
		return orikaMapperFacade.map(updatedUser, UpdateUserResponse.class);
	}

	private static void setUpperCase(User request){
		request.setFirstName(request.getFirstName().toUpperCase());
		request.setLastName(request.getLastName().toUpperCase());
	}
}
