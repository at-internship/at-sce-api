package com.agilethought.atsceapi.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

	public static boolean isValidString(String str) {

		if (str == null || str.isEmpty()) return false;
		// Check if str is comprised only of one or more whitespaces (unaccepted).
		Pattern patternString = Pattern.compile(
				"^\\s+$"
		);
		Matcher matcherString = patternString.matcher(str);
		return !matcherString.find();

	}

	static boolean isLowerCaseString(String str) {
		return str.equals(str.toLowerCase());

	}

	static boolean isValidEmail(String email) {
		Pattern patternEmail = Pattern.compile(
				"^[\\p{L}\\p{N}\\._%+-]+@[\\p{L}\\p{N}\\.\\-]+\\.[\\p{L}]{2,}$"
		);
		Matcher matcherEmail = patternEmail.matcher(email);
		return matcherEmail.find();
	}

	static boolean isValidPassword(String password) {
		Pattern patternPassword = Pattern.compile(
				// At least one numerical character
				// At least one lowercase character
				// At least one uppercase character
				// At least 10 characters long
				"^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{10,}$"
		);
		Matcher matcherPassword = patternPassword.matcher(password);
		return matcherPassword.find();
	}
}
