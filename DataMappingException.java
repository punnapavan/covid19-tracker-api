package com.covid.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DataMappingException extends Exception {

	private static final long serialVersionUID = -533293247482361933L;

	private final String message;
	private final String errorCode;

	public DataMappingException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		this.message = message;
		
	}

	@Override
	public String getMessage() {
		return message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	
}