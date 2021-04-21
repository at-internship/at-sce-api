package com.agilethought.atsceapi.validator.user;

import static com.agilethought.atsceapi.exception.ErrorMessage.EMAIL;
import static com.agilethought.atsceapi.exception.ErrorMessage.INVALID_INPUT;
import static com.agilethought.atsceapi.exception.ErrorMessage.MISSING_REQUIRED_INPUT;
import static com.agilethought.atsceapi.exception.ErrorMessage.PASSWORD;
import static com.agilethought.atsceapi.exception.ErrorMessage.VALIDATION_ERROR;
import static com.agilethought.atsceapi.validator.user.ValidationUtils.isValidEmail;
import static com.agilethought.atsceapi.validator.user.ValidationUtils.isValidPassword;
import static com.agilethought.atsceapi.validator.user.ValidationUtils.isValidString;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilethought.atsceapi.exception.BadRequestException;
import com.agilethought.atsceapi.exception.GlobalExceptionBody.ErrorDetails;
import com.agilethought.atsceapi.model.User;
import com.agilethought.atsceapi.repository.UserRepository;
import com.agilethought.atsceapi.validator.Validator;

@Service
public class UserValidator implements Validator<User> {

	private static final String TYPE = "Type";
	private static final String CORRECT_FORMAT_TYPE = "Number 1 for admin or number 2 " + "for normal user";

	private static final String FIRST_NAME = "First name";

	private static final String LAST_NAME = "Last name";

	private static final String CORRECT_FORMAT_EMAIL = "an_accepted-email.example@domain.com.mx";

	private static final String CORRECT_FORMAT_PASSWORD = "10 characters minimum with "
			+ "at least one lowercase letter, one uppercase letter, and one number.";

	private static final String STATUS = "Status of user";
	private static final String CORRECT_FORMAT_STATUS = "Number 0 for unavailable or " + "number 1 for available";

	@Autowired
	private UserRepository userRepository;

	@Override
	public void validate(User user) {
		List<ErrorDetails> errorDetails = new ArrayList<ErrorDetails>();
		validateType(user.getType(), errorDetails);
		validateFirstName(user.getFirstName(), errorDetails);
		validateLastName(user.getLastName(), errorDetails);
		validateEmail(user.getEmail(), errorDetails);
		validatePassword(user.getPassword(), errorDetails);
		validateStatus(user.getStatus(), errorDetails);
		if (CollectionUtils.isNotEmpty(errorDetails))
			throw new BadRequestException(VALIDATION_ERROR, errorDetails);
	}

	private void validateType(Integer type, List<ErrorDetails> errorDetails) {

		if (type == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, TYPE));
			error.setFieldName(TYPE);
			errorDetails.add(error);
			return;
		}
		if (type < 1 || type > 2) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, TYPE, CORRECT_FORMAT_TYPE));
			error.setFieldName(TYPE);
			errorDetails.add(error);
			return;
		}

	}

	private void validateFirstName(String firstName, List<ErrorDetails> errorDetails) {

		if (!isValidString(firstName)) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, FIRST_NAME));
			error.setFieldName(FIRST_NAME);
			errorDetails.add(error);
			return;
		}

	}

	private void validateLastName(String lastName, List<ErrorDetails> errorDetails) {

		if (!isValidString(lastName)) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, LAST_NAME));
			error.setFieldName(LAST_NAME);
			errorDetails.add(error);
			return;
		}

	}

	private void validateEmail(String email, List<ErrorDetails> errorDetails) {

		if (!isValidString(email)) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, EMAIL));
			error.setFieldName(EMAIL);
			errorDetails.add(error);
			return;
		}
		if (!isValidEmail(email)) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, EMAIL, CORRECT_FORMAT_EMAIL));
			error.setFieldName(EMAIL);
			errorDetails.add(error);
			return;
		}
	}

	private void validatePassword(String password, List<ErrorDetails> errorDetails) {

		if (!isValidString(password)) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, PASSWORD));
			error.setFieldName(PASSWORD);
			errorDetails.add(error);
			return;
		}
		if (!isValidPassword(password)) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, PASSWORD, CORRECT_FORMAT_PASSWORD));
			error.setFieldName(PASSWORD);
			errorDetails.add(error);
			return;
		}

	}

	private void validateStatus(Integer status, List<ErrorDetails> errorDetails) {

		if (status == null) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, STATUS));
			error.setFieldName(STATUS);
			errorDetails.add(error);
			return;
		}
		if (status < 0 || status > 1) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(INVALID_INPUT, STATUS, CORRECT_FORMAT_STATUS));
			error.setFieldName(STATUS);
			errorDetails.add(error);
			return;
		}

	}

}
