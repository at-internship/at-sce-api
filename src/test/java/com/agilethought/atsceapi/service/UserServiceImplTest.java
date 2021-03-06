package com.agilethought.atsceapi.service;

import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllUsersTest(){
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        List<User> result = userService.getAllUsers();
        assertNotNull(result);
    }
}
