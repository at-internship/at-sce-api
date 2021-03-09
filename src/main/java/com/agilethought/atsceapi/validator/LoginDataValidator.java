package com.agilethought.atsceapi.validator;

import com.agilethought.atsceapi.exception.UnauthorizedException;

public class LoginDataValidator implements ValidatorUserService{

	@Override
	public void validateEmail(String email) {
		// TODO Auto-generated method stub
		//
		//If something wrong happens throw an exception
		throw new UnauthorizedException("Unathorized");//Use your custom exception
		//Otherwise the flow will continue
	}

	@Override
	public void validatePassword(String password) {
		// TODO Auto-generated method stub
		//If something wrong happens throw an exception
		throw new UnauthorizedException("Unathorized");//Use your custom exception
		//Otherwise the flow will continues
	}

}
