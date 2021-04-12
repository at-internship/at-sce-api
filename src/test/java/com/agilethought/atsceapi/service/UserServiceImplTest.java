package com.agilethought.atsceapi.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.agilethought.atsceapi.service.implementation.UserServiceImpl;
import ma.glasnost.orika.MapperFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.agilethought.atsceapi.dto.user.UserDTO;
import com.agilethought.atsceapi.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MapperFacade userRepositoryMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void itShouldGetAllUsers(){
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        when(userRepositoryMapper.mapAsList(anyList(),any())).thenReturn(new ArrayList<>());
        List<UserDTO> result = userService.getAllUsers();
        assertNotNull(result);
    }
}
