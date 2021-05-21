package com.agilethought.atsceapi.service.implementation;

import com.agilethought.atsceapi.adaptor.sso.SSOAdaptor;
import com.agilethought.atsceapi.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private SSOAdaptor ssoAdaptor;

    @Override
    public void authorizeRequest(String authorization) throws Exception {
        String token = authorization.replace("Bearer ","").trim();
        ssoAdaptor.getTokenCheck(token);
    }
}
