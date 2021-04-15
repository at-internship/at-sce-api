package com.agilethought.atsceapi.validator.user;

import static com.agilethought.atsceapi.exception.ErrorMessage.*;
import static com.agilethought.atsceapi.validator.user.ValidationUtils.isValidPassword;
import static com.agilethought.atsceapi.validator.user.ValidationUtils.isValidString;

import com.agilethought.atsceapi.validator.Validator;
import org.springframework.stereotype.Service;

import com.agilethought.atsceapi.exception.BadRequestException;
import com.agilethought.atsceapi.dto.user.LoginRequest;
import com.agilethought.atsceapi.exception.UnauthorizedException;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class LoginValidator implements Validator<LoginRequest> {
	
	@Override
	public void validate(LoginRequest loginRequest) {

		validateEmail(loginRequest.getEmail());
		validatePassword(loginRequest.getPassword());

	}

	private void validateEmail(String email) {

		if (!isValidString(email)) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, EMAIL)
			);
		}
		if (!ValidationUtils.isValidEmail(email))
			throw new UnauthorizedException(INVALID_CREDENTIALS);

	}

	private void validatePassword(String password) {

		if (!isValidString(password)) {
			throw new BadRequestException(
					String.format(MISSING_REQUIRED_INPUT, PASSWORD)
			);
		}
		if (!isValidPassword(password)) {
			throw new UnauthorizedException(INVALID_CREDENTIALS);
		}

	}
}
