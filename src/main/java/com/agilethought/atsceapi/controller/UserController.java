package com.agilethought.atsceapi.controller;

import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/at-sce-api/api/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable String id){
         return userService.getUserById(id);
     }
}
