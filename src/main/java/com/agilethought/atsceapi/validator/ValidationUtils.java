package com.agilethought.atsceapi.validator;

public class ValidationUtils {
	public static boolean isStringLowerCase(String str) {
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

	public static boolean isValid(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}
}
