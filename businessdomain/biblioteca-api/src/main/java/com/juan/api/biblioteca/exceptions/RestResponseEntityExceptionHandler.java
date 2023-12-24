package com.juan.api.biblioteca.exceptions;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(IllegalArgumentException.class)
	protected ResponseEntity<Object> handleIllegalArgument(){
		return new ResponseEntity<Object>("Illegal Argument Exception", HttpStatus.PRECONDITION_FAILED);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<List> validationExceptionHandler(ConstraintViolationException e){
		List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
		
		e.getConstraintViolations().forEach(constraintViolation -> {
			errors.add(constraintViolation.getPropertyPath() + " : ");
		});
		
		return new ResponseEntity<>(errors, HttpStatus.ACCEPTED);
	}

}
