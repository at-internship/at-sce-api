package com.agilethought.atsceapi.service;

import com.agilethought.atsceapi.domain.CreateUserRequest;
import com.agilethought.atsceapi.domain.CreateUserResponse;

public interface UserService {

	CreateUserResponse createUser(CreateUserRequest request);

}
