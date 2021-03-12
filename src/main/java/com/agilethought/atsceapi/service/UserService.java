package com.agilethought.atsceapi.service;

import java.util.List;

import com.agilethought.atsceapi.dto.LoginData;
import com.agilethought.atsceapi.dto.NewUserRequest;
import com.agilethought.atsceapi.dto.UpdateUserRequest;
import com.agilethought.atsceapi.dto.NewUserResponse;
import com.agilethought.atsceapi.dto.UserDTO;
import com.agilethought.atsceapi.dto.UpdateUserResponse;


public interface UserService {
	List<UserDTO> getAllUsers();

	UserDTO loginMethod(LoginData loginData);
	
	NewUserResponse createUser(NewUserRequest request);

	UserDTO getUserById(String id);

	void deleteUserById(String id);
	
	UpdateUserResponse updateUser(UpdateUserRequest request, String id);

}