package com.agilethought.atsceapi.service;

import java.util.List;

import com.agilethought.atsceapi.model.LoginData;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.dto.NewUserRequest;
import com.agilethought.atsceapi.dto.NewUserResponse;

public interface UserService {
	List<User> getAllUsers();

	User loginMethod(LoginData loginData);
	
	NewUserResponse createUser(NewUserRequest request);

	User getUserById(String id);

	void deleteUserById(String id);
}