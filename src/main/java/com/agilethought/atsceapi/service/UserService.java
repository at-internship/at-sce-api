package com.agilethought.atsceapi.service;

import java.util.List;

import com.agilethought.atsceapi.domain.NewUserResponse;
import com.agilethought.atsceapi.domain.UpdateUserResponse;
import com.agilethought.atsceapi.domain.UserRequest;
import com.agilethought.atsceapi.model.LoginData;
import com.agilethought.atsceapi.model.User;

public interface UserService {
	List<User> getAllUsers();

	User loginMethod(LoginData loginData);
	
	NewUserResponse createUser(UserRequest request);

	User getUserById(String id);
	
	UpdateUserResponse updateUser(UserRequest request, String id);
}