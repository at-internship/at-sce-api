package com.agilethought.atsceapi.service;

import java.util.List;

import com.agilethought.atsceapi.dto.user.*;

public interface UserService {

	List<UserDTO> getAllUsers();

	LoginResponse loginUser(LoginRequest loginRequest);
	
	NewUserResponse createUser(NewUserRequest request);

	UserDTO getUserById(String id);

	void deleteUserById(String id);
	
	UpdateUserResponse updateUser(
			UpdateUserRequest request,
			String id
	);

}