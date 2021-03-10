package com.agilethought.atsceapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.agilethought.atsceapi.dto.LoginData;
import com.agilethought.atsceapi.dto.NewUserRequest;
import com.agilethought.atsceapi.dto.NewUserResponse;
import com.agilethought.atsceapi.dto.UserDTO;
import com.agilethought.atsceapi.service.UserService;

@RestController
@RequestMapping(value = "/api/v1")
public class UserController {
	@Autowired
	private UserService userService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/users")
	public List<UserDTO> findAllUsers() {
		return userService.getAllUsers();
	}

	@PostMapping(value = "/login")
	public UserDTO loginUser(@RequestBody LoginData loginData) {
		return userService.loginMethod(loginData);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
	public NewUserResponse postUser(@RequestBody NewUserRequest request) {
		return userService.createUser(request);
	}

	@GetMapping("/users/{id}")
	@ResponseStatus(HttpStatus.OK)
	public UserDTO getUserById(@PathVariable String id){
		return userService.getUserById(id);
	}

	@DeleteMapping(value = "/users/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUserById(@PathVariable String id){
		userService.deleteUserById(id);
	}

}