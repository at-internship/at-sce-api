package com.agilethought.atsceapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.agilethought.atsceapi.domain.CreateUserRequest;
import com.agilethought.atsceapi.domain.CreateUserResponse;
import com.agilethought.atsceapi.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/at-sce-api/api/v1/users", consumes = "application/json", produces = "application/json")
	public CreateUserResponse postUser(@RequestBody CreateUserRequest request) {
		return userService.createUser(request);
	}
}
