package com.covid.exception;

import org.springframework.http.HttpStatus;


public class DataValidationException extends Exception {

	private static final long serialVersionUID = -533293247482361933L;

	private final String message;
	private final String errorCode;
	private final HttpStatus statusCode;

	public DataValidationException(String message, String errorCode, HttpStatus statusCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
		this.statusCode = statusCode;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

}
