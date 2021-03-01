package com.agilethought.atsceapi.service;

import com.agilethought.atsceapi.domain.NewUserRequest;
import com.agilethought.atsceapi.domain.NewUserResponse;
import com.agilethought.atsceapi.model.LoginData;
import com.agilethought.atsceapi.model.User;

public interface UserService {

	User loginMethod(LoginData loginData);

	NewUserResponse createUser(NewUserRequest request);
}
