package com.agilethought.atsceapi.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.agilethought.atsceapi.dto.LoginData;
import com.agilethought.atsceapi.dto.NewUserRequest;
import com.agilethought.atsceapi.dto.NewUserResponse;
import com.agilethought.atsceapi.dto.UpdateUserResponse;
import com.agilethought.atsceapi.dto.UserDTO;
import com.agilethought.atsceapi.dto.UpdateUserRequest;
import com.agilethought.atsceapi.service.UserService;

@RestController
@RequestMapping(value = "/api/v1")
@Api(value = "Software Cost Estimation", tags = "SCE")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    @ApiOperation(value = "Get all users from the database of the application")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search in the database without parameters"),
            @ApiResponse(code = 201, message = "Resource created succefully"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access prohibited"),
            @ApiResponse(code = 404, message = "Not Found"),
    })
    public List<UserDTO> findAllUsers() {
        return userService.getAllUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/login")
    @ApiOperation(value = "Let the user log into the application")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search in the database without parameters"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access prohibited"),
    })
    public UserDTO loginUser(@RequestBody LoginData loginData) {
        return userService.loginMethod(loginData);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/users", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Create New User in the application")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource created succefully"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access prohibited"),
            @ApiResponse(code = 404, message = "Not Found"),
    })
    public NewUserResponse postUser(@RequestBody NewUserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Validation the session of the user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search in the database without parameters"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access prohibited"),
            @ApiResponse(code = 404, message = "Not Found"),
    })
    public UserDTO getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

	@DeleteMapping(value = "/users/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a User")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No Content"),
            @ApiResponse(code = 404, message = "Not Found"),
    })
	public void deleteUserById(@PathVariable String id){
		userService.deleteUserById(id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/users/{id}", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Update a User in the application")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Resource update succefully"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access prohibited"),
            @ApiResponse(code = 404, message = "Not Found"),
    })
	public UpdateUserResponse putUser(@RequestBody UpdateUserRequest request, @PathVariable String id) {
		return userService.updateUser(request,id);
	}
	
}