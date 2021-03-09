package com.agilethought.atsceapi.validator;

import com.agilethought.atsceapi.model.User;

public interface ValidatorUserService {
	public void validateEmail(String email);
	public void validatePassword(String password);
	public void validateStatus(User user);
}
