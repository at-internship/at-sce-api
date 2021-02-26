package com.agilethought.atsceapi.service;

import com.agilethought.atsceapi.model.LoginData;
import com.agilethought.atsceapi.model.User;

public interface UserService {

    User loginMethod (LoginData loginData);
}
