package com.agilethought.atsceapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilethought.atsceapi.domain.UserRequest;
import com.agilethought.atsceapi.domain.NewUserResponse;
import com.agilethought.atsceapi.domain.UpdateUserResponse;
import com.agilethought.atsceapi.exception.NotFoundException;
import com.agilethought.atsceapi.exception.UnauthorizedException;
import com.agilethought.atsceapi.model.LoginData;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

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
	public NewUserResponse createUser(UserRequest request) {
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
	
	private static boolean isStringLowerCase(String str) {
		// convert String to char array
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			// if the character is a letter
			if (Character.isLetter(charArray[i])) {
				// if any character is not in lower case, return false
				if (!Character.isLowerCase(charArray[i]))
					return false;
			}
		}
		return true;
	}

	private static boolean isValid(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	@Override
	//update
	public UpdateUserResponse updateUser(UserRequest request, String id) {
		UpdateUserResponse response = new UpdateUserResponse();
		User user = new User();
		Optional<User> userFound = userRepository.findById(id);
		if(userFound.isPresent()) {
			user.setId(id);
			user.setType(request.getType());
			user.setFirstName(request.getFirstName());
			user.setLastName(request.getLastName());
			user.setEmail(request.getEmail());
			user.setPassword(request.getPassword());
			user.setStatus(request.getStatus()); 			
			
			userRepository.save(user);
			
			response.setId(user.getId());
			response.setType(user.getType());
			response.setFirstName(user.getFirstName());
			response.setLastName(user.getLastName());
			response.setEmail(user.getEmail());
			response.setPassword(user.getPassword());
			response.setStatus(user.getStatus());
			
			return response;
		}else {
			throw new NotFoundException("User Not Found with id: " + id);
		}
				
	}
}