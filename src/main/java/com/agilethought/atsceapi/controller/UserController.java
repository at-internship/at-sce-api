package com.agilethought.atsceapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.agilethought.atsceapi.domain.NewUserRequest;
import com.agilethought.atsceapi.domain.NewUserResponse;
import com.agilethought.atsceapi.model.LoginData;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/login")
	public User loginUser(@RequestBody LoginData loginData) {
		return userService.loginMethod(loginData);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
	public NewUserResponse postUser(@RequestBody NewUserRequest request) {
		return userService.createUser(request);
	}

}
