package com.agilethought.atsceapi.exception;

public class ErrorMessage {

    public static final String BAD_REQUEST_MESSAGE = "The field ";
    public static final String BAD_REQUEST_MESSAGE_TYPE = "needs to be 1 or 2";
    public static final String BAD_REQUEST_MESSAGE_FIRST_NAME_LASTNAME = "is wrong because you need to type it";
    public static final String BAD_REQUEST_MESSAGE_EMAIL_PASSWORD = "is wrong because it doesn't have the correct format";
    public static final String BAD_REQUEST_MESSAGE_EMAIL_IN_DB = "can not be store, because it already exist in the Database";
}
