package com.agilethought.atsceapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilethought.atsceapi.domain.LoginData;
import com.agilethought.atsceapi.domain.NewUserRequest;
import com.agilethought.atsceapi.domain.NewUserResponse;
import com.agilethought.atsceapi.dto.UserDTO;
import com.agilethought.atsceapi.exception.NotFoundException;
import com.agilethought.atsceapi.exception.UnauthorizedException;
import com.agilethought.atsceapi.model.User;
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
	
	@Override
	public UserDTO loginMethod(LoginData loginData) {

		if (isValid(loginData.getEmail()) && isStringLowerCase(loginData.getEmail())) {
			List<User> users = userRepository.findUsersByEmail(loginData.getEmail(), loginData.getPassword());
			if (!users.isEmpty()) {
				log.info("Get user from Database " + users.get(0));
				return orikaMapperFacade.map(users.get(0), UserDTO.class);
			}
		}
		log.info("Email not valid " + loginData.getEmail());
		throw new UnauthorizedException("User not authorized");
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
}