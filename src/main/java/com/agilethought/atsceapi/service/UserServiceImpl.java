package com.agilethought.atsceapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilethought.atsceapi.domain.CreateUserRequest;
import com.agilethought.atsceapi.domain.CreateUserResponse;
import com.agilethought.atsceapi.model.Users;
import com.agilethought.atsceapi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	public CreateUserResponse createUser(CreateUserRequest request) {

		CreateUserResponse response = new CreateUserResponse();
		Users user = new Users();
		user.setType(request.getType());
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setEmail(request.getEmail());
		user.setPassword(request.getPassword());
		user.setStatus(request.getStatus());
		Users savedUsers = repository.save(user);
		response.setId(savedUsers.getId());
		return response;
	}

}
