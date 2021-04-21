package com.agilethought.atsceapi.service.implementation;

import static com.agilethought.atsceapi.exception.ErrorMessage.INVALID_CREDENTIALS;
import static com.agilethought.atsceapi.exception.ErrorMessage.UNAVAILABLE_ENTITY;
import static com.agilethought.atsceapi.exception.ErrorMessage.NOT_FOUND_RESOURCE;
import static com.agilethought.atsceapi.exception.ErrorMessage.ALREADY_EXISTING_EMAIL;
import static com.agilethought.atsceapi.exception.ErrorMessage.USER;

import java.util.List;
import java.util.Optional;

import com.agilethought.atsceapi.dto.user.*;
import com.agilethought.atsceapi.exception.BadRequestException;
import com.agilethought.atsceapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.validator.Validator;
import com.agilethought.atsceapi.validator.user.UserValidator;
import com.agilethought.atsceapi.exception.NotFoundException;
import com.agilethought.atsceapi.exception.UnauthorizedException;
import com.agilethought.atsceapi.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import ma.glasnost.orika.MapperFacade;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MapperFacade orikaMapperFacade;

	@Autowired
	private Validator<LoginRequest> loginValidator;

	@Autowired
	private UserValidator userValidator;

	@Override
	public LoginResponse loginUser(LoginRequest loginRequest) {

		loginValidator.validate(loginRequest);
		List<User> users = userRepository.findUserWithCredentials(
				loginRequest.getEmail(),
				loginRequest.getPassword()
		);
		if (!users.isEmpty()) {
			log.info("UserServiceImpl.loginUser: got user " + users.get(0) + " from Database");
			User user = users.get(0);
			if (user.getStatus() == 0)
				throw new UnauthorizedException(
						String.format(UNAVAILABLE_ENTITY, USER)
				);
			return orikaMapperFacade.map(user, LoginResponse.class);
		}
		throw new UnauthorizedException(INVALID_CREDENTIALS);

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
		throw new NotFoundException(
				String.format(NOT_FOUND_RESOURCE, USER, id)
		);

	}

	@Override
	public void deleteUserById(String id) {

		Optional<User> userFoundById = userRepository.findById(id);
		if (!userFoundById.isPresent())
			throw new NotFoundException(
					String.format(NOT_FOUND_RESOURCE, USER, id)
			);
		userRepository.deleteById(id);

	}

	@Override
	public NewUserResponse createUser(NewUserRequest request) {

		User user = orikaMapperFacade.map(request, User.class);
		userValidator.validate(user);
		setLetterCases(user);
		User savedUsers = userRepository.save(user);
		return orikaMapperFacade.map(savedUsers, NewUserResponse.class);

	}

	@Override
	public UpdateUserResponse updateUser(UpdateUserRequest request, String id) {

		Optional<User> userFoundById = userRepository.findById(id);
		if (!userFoundById.isPresent())
			throw new NotFoundException(
					String.format(NOT_FOUND_RESOURCE, USER, id)
			);
		request.setId(id);
		User user = orikaMapperFacade.map(request, User.class);
		userValidator.validate(user);
		setLetterCases(user);
		User updatedUser = userRepository.save(user);
		return orikaMapperFacade.map(updatedUser, UpdateUserResponse.class);

	}

	private static void setLetterCases(User request){

		request.setFirstName(request.getFirstName().toUpperCase());
		request.setLastName(request.getLastName().toUpperCase());
		request.setEmail(request.getEmail().toLowerCase());

	}

}
