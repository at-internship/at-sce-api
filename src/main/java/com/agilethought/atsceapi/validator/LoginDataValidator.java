package com.agilethought.atsceapi.validator;

import com.agilethought.atsceapi.exception.UnauthorizedException;
import com.agilethought.atsceapi.model.User;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LoginDataValidator implements ValidatorUserService{

	@Override
	public void validateEmail(String email) {
		if(!isValid(email) && !isStringLowerCase(email))
			throw new UnauthorizedException("Unathorized");
	}

	@Override
	public void validatePassword(String password) {
		if(password.isEmpty())
			throw new UnauthorizedException("Unathorized");
	}
	
	@Override
	public void validateStatus(User user) {
		if(user.getStatus() == 0) {
			throw new UnauthorizedException("Unathorized");
		}
		
	}
	
	private boolean isStringLowerCase(String str) {
		// convert String to char array
		char[] charArray = str.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			// if the character is a letter
			if (Character.isLetter(charArray[i])) {
				// if any character is not in lower case, return false
				if (!Character.isLowerCase(charArray[i]))
					return false;
			}
		}
		return true;
	}

	private boolean isValid(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}
}
