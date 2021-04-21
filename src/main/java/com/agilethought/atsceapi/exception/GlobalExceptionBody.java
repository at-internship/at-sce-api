package com.agilethought.atsceapi.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class GlobalExceptionBody {
	private LocalDateTime timestamp;
	private Integer status;
	private String error;
	private String message;
	private String path;
	private List<ErrorDetails> details;

	@Data
	public static class ErrorDetails {
		private String fieldName;
		private String errorMessage;
	}
}