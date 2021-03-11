package com.agilethought.atsceapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.agilethought.atsceapi.domain.NewUserResponse;
import com.agilethought.atsceapi.domain.UpdateUserResponse;
import com.agilethought.atsceapi.domain.UserRequest;
import com.agilethought.atsceapi.model.LoginData;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.service.UserService;

@RestController
@RequestMapping(value = "/api/v1/")
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
	public NewUserResponse postUser(@RequestBody UserRequest request) {
		return userService.createUser(request);
	}

	@GetMapping("/users/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User getUserById(@PathVariable String id){
		return userService.getUserById(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/users/{id}", consumes = "application/json", produces = "application/json")
	public UpdateUserResponse putUser(@RequestBody UserRequest request, @PathVariable String id) {
		return userService.updateUser(request,id);
	}
}