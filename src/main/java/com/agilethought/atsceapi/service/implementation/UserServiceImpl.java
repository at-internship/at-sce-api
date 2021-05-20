package com.agilethought.atsceapi.service.implementation;

import static com.agilethought.atsceapi.exception.ErrorMessage.INVALID_CREDENTIALS;
import static com.agilethought.atsceapi.exception.ErrorMessage.UNAVAILABLE_ENTITY;
import static com.agilethought.atsceapi.exception.ErrorMessage.NOT_FOUND_RESOURCE;
import static com.agilethought.atsceapi.exception.ErrorMessage.USER;

import java.util.List;
import java.util.Optional;

import com.agilethought.atsceapi.dto.user.*;
import com.agilethought.atsceapi.service.UserService;
import com.agilethought.atsceapi.service.security.RsaPasswordEncoder;
import com.agilethought.atsceapi.validator.user.LoginValidator;
import com.agilethought.atsceapi.validator.user.NewUserValidator;
import com.agilethought.atsceapi.validator.user.UpdateUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.validator.user.UserDataValidator;
import com.agilethought.atsceapi.exception.BadRequestException;
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
	private LoginValidator loginValidator;

	@Autowired
	private NewUserValidator newUserValidator;

	@Autowired
	private UpdateUserValidator updateUserValidator;
	
	@Autowired
	private RsaPasswordEncoder rsaPasswordEncoder;

	@Override
	public LoginResponse loginUser(LoginRequest loginRequest) {
		String decryptedPassword = rsaPasswordEncoder.decode(loginRequest.getPassword());
		loginRequest.setPassword(decryptedPassword);
		loginValidator.validate(loginRequest);

		User user = userRepository.findByEmail(loginRequest.getEmail());
		boolean bothPasswordsMatch = rsaPasswordEncoder.matches(decryptedPassword, user.getPassword());
		if (bothPasswordsMatch) { //Grant Access
			log.info("UserServiceImpl.loginUser: got user " + user + " from Database");
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
		try{
			user.setPassword(rsaPasswordEncoder.decode(request.getPassword()));
		} catch (Exception e){
			throw new BadRequestException("Error decrypting data");
		}
		newUserValidator.validate(user);
		setLetterCases(user);
		user.setPassword(request.getPassword());
		User savedUsers = userRepository.save(user);
		return orikaMapperFacade.map(savedUsers, NewUserResponse.class);

	}

	@Override
	public UpdateUserResponse updateUserById(UpdateUserRequest request, String id) {

		request.setId(id);
		User userUpdatedFields = orikaMapperFacade.map(request, User.class);
		updateUserValidator.validate(userUpdatedFields);
		setLetterCases(userUpdatedFields);
		User updatedUser = userRepository.save(userUpdatedFields);
		return orikaMapperFacade.map(updatedUser, UpdateUserResponse.class);

	}

	private static void setLetterCases(User request){

		request.setFirstName(request.getFirstName().toUpperCase());
		request.setLastName(request.getLastName().toUpperCase());
		request.setEmail(request.getEmail().toLowerCase());

	}

}
