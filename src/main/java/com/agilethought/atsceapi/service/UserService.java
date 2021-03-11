package com.agilethought.atsceapi.service;

import java.util.List;

import com.agilethought.atsceapi.dto.LoginData;
import com.agilethought.atsceapi.dto.UserRequest;
import com.agilethought.atsceapi.dto.NewUserResponse;
import com.agilethought.atsceapi.dto.UserDTO;
import com.agilethought.atsceapi.dto.UpdateUserResponse;

public interface UserService {
	List<UserDTO> getAllUsers();

	UserDTO loginMethod(LoginData loginData);
	
	NewUserResponse createUser(UserRequest request);

	UserDTO getUserById(String id);

	void deleteUserById(String id);
	
	UpdateUserResponse updateUser(UserRequest request, String id);
}