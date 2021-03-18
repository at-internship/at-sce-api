package com.agilethought.atsceapi.exception;

public class ErrorMessage {

    public static final String BAD_REQUEST_MESSAGE = "The field ";
    public static final String BAD_REQUEST_MESSAGE_TYPE = "needs to be 1 or 2";
    public static final String BAD_REQUEST_MESSAGE_FIRST_NAME_LASTNAME = "is required and you don't type it";
    public static final String BAD_REQUEST_MESSAGE_EMAIL = "is not valid, because it doesn't have the correct format, " +
            "try without capital letters and the format like this xxx@domail.xxx";
    public static final String BAD_REQUEST_MESSAGE_PASSWORD = "is not valid, because it doesn't have the correct format, " +
            "try with at least 10 characters, a numeric value and without capital letters characters";
    public static final String BAD_REQUEST_MESSAGE_EMAIL_IN_DB = "can not be store, because it already exist in the Database";
    public static final String BAD_REQUEST_MESSAGE_STATUS = "The status needs to be 0 or 1";
}
