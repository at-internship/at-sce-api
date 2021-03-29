package com.agilethought.atsceapi.exception;

public class ErrorMessage {

    public static final String BAD_REQUEST_MESSAGE = "The field ";
    public static final String BAD_REQUEST_MESSAGE_TYPE = "needs to be 1 or 2";
    public static final String BAD_REQUEST_MESSAGE_FIRST_NAME_LASTNAME = "is required and you didn't type it";
    public static final String BAD_REQUEST_MESSAGE_EMAIL = "is not valid, because it doesn't have the correct format, " +
            "try without capital letters and the format like this xxx@domail.xxx";
    public static final String BAD_REQUEST_MESSAGE_PASSWORD = "is not valid, because it doesn't have the correct format, " +
            "try with at least 10 characters, a numeric value and without capital letters characters";
    public static final String BAD_REQUEST_MESSAGE_EMAIL_IN_DB = "can not be store, because it already exists in the Database, try with another email";
    public static final String BAD_REQUEST_MESSAGE_STATUS = "The status needs to be 0 or 1";
    public static final String BAD_REQUEST_MESSAGE_UNMATCHED_EMAIL = "Email provided does not match";
    public static final String BAD_REQUEST_MESSAGE_HISTORY_TYPE = "needs to be either 1, 2, 3 or 4";
	public static final String BAD_REQUEST_MESSAGE_USER_ID = "needs to be a valid user id";
	public static final String BAD_REQUEST_MESSAGE_REQUIRED_NUMERIC = "needs to have a numeric value of 0 or more";
}
