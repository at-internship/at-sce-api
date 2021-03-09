package com.agilethought.atsceapi.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.agilethought.atsceapi.service.UserService;
import com.agilethought.atsceapi.model.LoginData;
import com.agilethought.atsceapi.model.User;
import org.springframework.web.bind.annotation.*;
import com.agilethought.atsceapi.domain.NewUserRequest;
import com.agilethought.atsceapi.domain.NewUserResponse;

@RestController
@RequestMapping(value = "/at-sce-api/api/v1")
public class UserController {
	@Autowired
	private UserService userService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/users")
	public List<User> findAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping(value = "/login")
	public User loginUser(@RequestBody LoginData loginData) {
		return userService.loginMethod(loginData);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
	public NewUserResponse postUser(@RequestBody NewUserRequest request) {
		return userService.createUser(request);
	}

	@GetMapping("/users/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User getUserById(@PathVariable String id){
		return userService.getUserById(id);
	}

	@DeleteMapping(value = "/users/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUserById(@PathVariable String id){
		userService.deleteUserById(id);
	}

}