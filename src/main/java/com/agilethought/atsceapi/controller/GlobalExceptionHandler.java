package com.agilethought.atsceapi.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.agilethought.atsceapi.exception.BadRequestException;
import com.agilethought.atsceapi.exception.GlobalExceptionBody;
import com.agilethought.atsceapi.exception.NotFoundException;
import com.agilethought.atsceapi.exception.UnauthorizedException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	final static String URI_LABEL = "uri=";

	@ExceptionHandler(value = { NotFoundException.class })
	protected ResponseEntity<Object> handleNotFound(NotFoundException ex, WebRequest request) {
		GlobalExceptionBody body = new GlobalExceptionBody();
		body.setTimestamp(LocalDateTime.now());
		body.setStatus(HttpStatus.NOT_FOUND.value());
		body.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
		body.setMessage(ex.getMessage());
		body.setPath(request.getDescription(false).replace(URI_LABEL, ""));
		return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(value = { BadRequestException.class })
	protected ResponseEntity<Object> handleBadRequest(BadRequestException ex, WebRequest request) {
		GlobalExceptionBody body = new GlobalExceptionBody();
		body.setTimestamp(LocalDateTime.now());
		body.setStatus(HttpStatus.BAD_REQUEST.value());
		body.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
		body.setMessage(ex.getMessage());
		body.setPath(request.getDescription(false).replace(URI_LABEL, ""));
		body.setDetails(ex.getErrorDetails());
		return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = { UnauthorizedException.class })
	protected ResponseEntity<Object> handleBadRequest(UnauthorizedException ex, WebRequest request) {
		GlobalExceptionBody body = new GlobalExceptionBody();
		body.setTimestamp(LocalDateTime.now());
		body.setStatus(HttpStatus.UNAUTHORIZED.value());
		body.setError(HttpStatus.UNAUTHORIZED.getReasonPhrase());
		body.setMessage(ex.getMessage());
		body.setPath(request.getDescription(false).replace(URI_LABEL, ""));
		return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
	}

	@ExceptionHandler(value = { Exception.class })
	protected ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request) {
		GlobalExceptionBody body = new GlobalExceptionBody();
		body.setTimestamp(LocalDateTime.now());
		body.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		body.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		body.setMessage(ex.getMessage());
		body.setPath(request.getDescription(false).replace(URI_LABEL, ""));
		return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
