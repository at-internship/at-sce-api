package com.agilethought.atsceapi.controller;

import com.agilethought.atsceapi.exceptions.BadRequestException;
import com.agilethought.atsceapi.model.LoginData;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.service.UserService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/at-sce-api/api/v1")
@Api(value = "Software Cost Estimation API")
public class UserController {


  @Autowired
  private UserService userService;


  @PostMapping(value= "/login")
  @ApiOperation(value= "/login")
  @ApiResponses(value = {@ApiResponse(code=200,message = "Sucessful Operation"),
          @ApiResponse(code=400,message = "bad request"),
          @ApiResponse(code=404,message = "not found"),
          @ApiResponse(code=409,message = "conflict"),
          @ApiResponse(code=500,message = "internal server error"),
  })
    public User loginUser(@Valid @RequestBody LoginData loginData) throws BadRequestException {

      return userService.loginMethod (loginData);

  }
}
