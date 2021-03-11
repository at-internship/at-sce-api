package com.agilethought.atsceapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilethought.atsceapi.dto.LoginData;
import com.agilethought.atsceapi.dto.NewUserResponse;
import com.agilethought.atsceapi.dto.UpdateUserResponse;
import com.agilethought.atsceapi.dto.UserDTO;
import com.agilethought.atsceapi.dto.UserRequest;
import com.agilethought.atsceapi.exception.NotFoundException;
import com.agilethought.atsceapi.exception.UnauthorizedException;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.repository.UserRepository;
import com.agilethought.atsceapi.validator.LoginValidator;
import com.agilethought.atsceapi.validator.Validator;

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
	private Validator<LoginData> loginValidator;
	
	@Override
	public UserDTO loginMethod(LoginData loginData) {
		loginValidator.validate(loginData);
		List<User> users = userRepository.findUsersByEmail(loginData.getEmail(), loginData.getPassword());
		if (!users.isEmpty()) {
			log.info("Get user from Database " + users.get(0));
			User user = users.get(0);
			if(user.getStatus() == 0)
				throw new UnauthorizedException("Unauthorized");
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
	public void deleteUserById(String id) {
		Optional<User> usFound = userRepository.findById(id);
		if (!usFound.isPresent())
			throw new NotFoundException("User Not Found with id: " + id);
		else
		userRepository.deleteById(id);
	}

	@Override
	public NewUserResponse createUser(UserRequest request) {
		User user = orikaMapperFacade.map(request, User.class);
		User savedUsers = userRepository.save(user);

		return orikaMapperFacade.map(savedUsers, NewUserResponse.class);
	}
	
	@Override
	public UpdateUserResponse updateUser(UserRequest request, String Id) {
		UpdateUserResponse response = new UpdateUserResponse();
		User user = orikaMapperFacade.map(request, User.class);
		Optional<User> userFound = userRepository.findById(Id);
		if(userFound.isPresent()) {
			user.setId(Id);
			user.setType(request.getType());
			user.setFirstName(request.getFirstName());
			user.setLastName(request.getLastName());
			user.setEmail(request.getEmail());
			user.setPassword(request.getPassword());
			user.setStatus(request.getStatus()); 			
			
			User savedUsers = userRepository.save(user);
			
			response.setId(user.getId());
			response.setType(user.getType());
			response.setFirstName(user.getFirstName());
			response.setLastName(user.getLastName());
			response.setEmail(user.getEmail());
			response.setPassword(user.getPassword());
			response.setStatus(user.getStatus());
			
			return orikaMapperFacade.map(savedUsers, UpdateUserResponse.class);
		
		}else {
			throw new NotFoundException("User Not Found with id: " + Id);
		}
    
	}
}