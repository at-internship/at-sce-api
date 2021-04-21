package com.agilethought.atsceapi.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.agilethought.atsceapi.exception.GlobalExceptionBody.ErrorDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
	private List<ErrorDetails> errorDetails;
	
    public BadRequestException(String message) {
        super(message);
    }
    
    public BadRequestException(String message, List<ErrorDetails> details) {
    	super(message);
    	this.errorDetails = details;
    }
}
