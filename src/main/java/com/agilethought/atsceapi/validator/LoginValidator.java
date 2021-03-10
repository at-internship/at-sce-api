package com.agilethought.atsceapi.validator;

import org.codehaus.plexus.component.annotations.Component;

import com.agilethought.atsceapi.dto.LoginData;
import com.agilethought.atsceapi.exception.UnauthorizedException;
import com.agilethought.atsceapi.model.User;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LoginValidator implements Validator<LoginData>{
	
	@Override
	public void validate(LoginData loginData) {
		validateEmail(loginData.getEmail());
		validatePassword(loginData.getPassword());
	}
	
	private void validateEmail(String email) {
		if(!ValidationUtils.isValid(email) && !ValidationUtils.isStringLowerCase(email))
			throw new UnauthorizedException("Unauthorized");
	}

	private void validatePassword(String password) {
		if(password.isEmpty())
			throw new UnauthorizedException("Unauthorized");
	}
}
