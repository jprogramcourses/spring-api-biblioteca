package com.juan.api.biblioteca.exceptions;

public enum ErrorCode {
	
	GENERIC_ERROR("Error_001", "Generic error");
	
	private String errCode;
	private String errMsgKey;
	
	private ErrorCode(final String errCode, final String errMsgKey) {
		this.errCode = errCode;
		this.errMsgKey = errMsgKey;
	}

	public String getErrCode() {
		return errCode;
	}

	public String getErrMsgKey() {
		return errMsgKey;
	}
	
}
