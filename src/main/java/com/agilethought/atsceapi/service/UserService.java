package com.agilethought.atsceapi.service;
import com.agilethought.atsceapi.domain.NewUserRequest;
import com.agilethought.atsceapi.domain.NewUserResponse;

public interface UserService {
	NewUserResponse createUser(NewUserRequest request);
}
