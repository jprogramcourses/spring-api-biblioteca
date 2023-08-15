package com.juan.api.biblioteca.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {
	
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	
	private String message;
	
	private Integer status;

}
