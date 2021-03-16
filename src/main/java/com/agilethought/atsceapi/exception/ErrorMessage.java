package com.agilethought.atsceapi.exception;

public class ErrorMessage {

    public static final String badRequestMessage = "The field ";
    public static final String badRequestMessageType = "needs to be 1 or 2";
    public static final String badRequestMessageFirstNameLastName = "is wrong because you need to type it";
    public static final String badRequestMessageEmailPassword = "is wrong because it doesn't have the correct format";
    public static final String badRequestMessageEmailInDB = "can not be store, because it already exist in the Database";
}
