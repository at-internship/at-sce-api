package com.agilethought.atsceapi.controller;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.agilethought.atsceapi.dto.user.*;
import com.agilethought.atsceapi.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private static final String REQUEST_MAPPING = "/api/v1";

    @Test
    public void testFindAllUsers() throws Exception {

        String getMapping = "/users";
        when(userService.getAllUsers()).thenReturn(new ArrayList<>());
        mockMvc.perform(
                get(REQUEST_MAPPING + getMapping)
                    .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testLoginUser() throws Exception {

        String postMapping = "/login";
        when(userService.loginUser(any())).thenReturn(new LoginResponse());
        mockMvc.perform(
                post(REQUEST_MAPPING + postMapping)
                        .content("{}")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testPostUser() throws Exception {

        String postMapping = "/users";
        when(userService.createUser(any(NewUserRequest.class))).thenReturn(new NewUserResponse());
        mockMvc.perform(
                post(REQUEST_MAPPING + postMapping)
                        .content("{}")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void testGetUserById() throws Exception {

        String getMapping = "/users/1234";
        when(userService.getUserById(anyString())).thenReturn(new UserDTO());
        mockMvc.perform(
                get(REQUEST_MAPPING + getMapping)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void testDeleteUserById() throws Exception {

        String deleteMapping = "/users/1234";
        doNothing().when(userService).deleteUserById(anyString());
        mockMvc.perform(
                delete(REQUEST_MAPPING + deleteMapping)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    public void testPutUser() throws Exception {

        String putMapping = "/users/1234";
        when(userService.updateUser(any(UpdateUserRequest.class), anyString()))
                .thenReturn(new UpdateUserResponse());
        mockMvc.perform(
                put(REQUEST_MAPPING + putMapping)
                        .content("{}")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}
