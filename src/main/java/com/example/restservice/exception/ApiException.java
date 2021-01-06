package com.example.restservice.exception;

public class ApiException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private int code;
	private String errorDetail;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getErrorDetail() {
		return errorDetail;
	}
	public void setErrorDetail(String errorDetail) {
		this.errorDetail = errorDetail;
	}

	public ApiException(int code, String errorDetail) {
		this.code = code;
		this.errorDetail = errorDetail;
	}
}
