package com.agilethought.atsceapi.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

	public static boolean isStringValid(String str) {
		if(str == null || str.isBlank()) {
			return false;
		}
		return true;
	}

	static boolean isStringLowerCase(String str) {
		return str.equals(str.toLowerCase());

	}

	static boolean isValidEmail(String email) {
		Pattern patternEmail = Pattern.compile(
				"^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$"
		);
		Matcher matcherEmail = patternEmail.matcher(email);
		return matcherEmail.find();
	}

	static boolean isValidPassword(String password) {
		Pattern patternPassword = Pattern.compile(
				"^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{10,}$"
		);
		Matcher matcherPassword = patternPassword.matcher(password);
		return matcherPassword.find();
	}
}
