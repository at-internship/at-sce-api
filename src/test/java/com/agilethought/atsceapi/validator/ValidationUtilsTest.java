package com.agilethought.atsceapi.validator;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidationUtilsTest {

    ValidationUtils validationUtils = new ValidationUtils();

    @Test
    public void isValidStringTest(){

        assertEquals(true,ValidationUtils.isValidString("hello"));
        assertEquals(false,ValidationUtils.isValidString("  "));
        assertEquals(false,ValidationUtils.isValidString(null));
        assertEquals(false,ValidationUtils.isValidString(""));
    }

    @Test
    public void isLowerCaseStringTest(){
        assertEquals(true,ValidationUtils.isLowerCaseString("test"));
        assertEquals(false,ValidationUtils.isLowerCaseString("Test"));
        assertEquals(false,ValidationUtils.isLowerCaseString("TEST"));
    }

    @Test
    public void isValidEmailTest(){
        assertEquals(true,ValidationUtils.isValidEmail("baz@example.com"));
        assertEquals(true,ValidationUtils.isValidEmail("baz-op@example.com"));
        assertEquals(false,ValidationUtils.isValidEmail("baz-op@example"));
        assertEquals(false,ValidationUtils.isValidEmail("baz-opexample.com"));
        assertEquals(false,ValidationUtils.isValidEmail("  "));

    }

    @Test
    public void  isValidPasswordTest(){
        assertEquals(true,ValidationUtils.isValidPassword("jacocoPluggin12"));
        assertEquals(true,ValidationUtils.isValidPassword("JACOCOPLUGGIn1"));
        assertEquals(false,ValidationUtils.isValidPassword("JACOCO"));
        assertEquals(false,ValidationUtils.isValidPassword("jacocopluggin"));
        assertEquals(false,ValidationUtils.isValidPassword("12345678910"));
        assertEquals(false,ValidationUtils.isValidPassword("JACOCOPLUGGIN"));
    }
}
