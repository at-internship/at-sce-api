package com.agilethought.atsceapi.exception;

public class ErrorMessage {

    public static final String MISSING_REQUIRED_INPUT = "Required field %s is missing.";
    public static final String ID = "Id";

    public static final String INVALID_INPUT = "Invalid input on field %s. Correct format is: %s";
    public static final String CORRECT_FORMAT_NUMERIC = "A number with or " +
            "without a decimal point.";

    public static final String EMAIL = "Email";
    public static final String ALREADY_EXISTING_EMAIL = "Email %s is already in use, " +
            "try with another one or login.";

    public static final String PASSWORD = "Password";

    public static final String NOT_FOUND_RESOURCE = "%s was not found with the given id: %s";

    public static final String HISTORY = "History";
    public static final String USER = "User";

    public static final String INVALID_CREDENTIALS = "Invalid login credentials.";
    public static final String UNAVAILABLE_ENTITY = "This %s is currently unavailable.";
    public static final String VALIDATION_ERROR = "One or more fields are invalid";
}
