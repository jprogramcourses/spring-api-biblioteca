package com.juan.api.biblioteca.exceptions;

public class ErrorUtils {
	
	private ErrorUtils() {
		
	}
	
	public static Error createError(final String errorCode, final String message, Integer status) {
		Error error = new Error();
		error.setErrorCode(errorCode);
		error.setMessage(message);
		error.setStatus(status);
		return error;
	}

}
