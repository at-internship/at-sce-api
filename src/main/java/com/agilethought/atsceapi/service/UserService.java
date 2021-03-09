package com.agilethought.atsceapi.service;

import java.util.List;

import com.agilethought.atsceapi.domain.LoginData;
import com.agilethought.atsceapi.domain.NewUserRequest;
import com.agilethought.atsceapi.domain.NewUserResponse;
import com.agilethought.atsceapi.dto.UserDTO;

public interface UserService {
	List<UserDTO> getAllUsers();

	UserDTO loginMethod(LoginData loginData);
	
	NewUserResponse createUser(NewUserRequest request);

	UserDTO getUserById(String id);
}