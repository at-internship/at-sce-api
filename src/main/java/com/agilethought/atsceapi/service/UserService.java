package com.agilethought.atsceapi.service;

import java.util.List;

import com.agilethought.atsceapi.model.LoginData;
import com.agilethought.atsceapi.model.User;

public interface UserService {
	List<User> getAllUsers();

	User loginMethod(LoginData loginData);
}