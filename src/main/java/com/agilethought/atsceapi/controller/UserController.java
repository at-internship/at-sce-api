package com.agilethought.atsceapi.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import com.agilethought.atsceapi.service.UserService;
import com.agilethought.atsceapi.model.UserModel;

@RestController
public class UserController {
	@Autowired
	private UserService service;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/at-sce-api/api/v1/users")
	public List<UserModel> findAllUsers() {
		 return service.getAllUsers();	
	}
}