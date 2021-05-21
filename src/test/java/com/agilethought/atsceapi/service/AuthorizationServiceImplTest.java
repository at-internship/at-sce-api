package com.agilethought.atsceapi.service;

import com.agilethought.atsceapi.adaptor.sso.SSOAdaptorImpl;
import com.agilethought.atsceapi.service.implementation.AuthorizationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class AuthorizationServiceImplTest {

    @Mock
    private SSOAdaptorImpl ssoAdaptor;

    @InjectMocks
    private AuthorizationServiceImpl authorizationService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void authorizeRequestSuccessTest() throws Exception {
        String bearerToken = "Bearer AuthorizationToken";
        when(ssoAdaptor.getTokenCheck(anyString())).thenReturn(new ResponseEntity<>("Valid Token", HttpStatus.OK));
        authorizationService.authorizeRequest(bearerToken);
        assertNotNull(bearerToken);
    }

}