package com.example.restservice.exception;

import com.example.restservice.response.Status;

public class ApiRecordNotFoundException extends ApiException {
	private static final long serialVersionUID = 1L;

	public ApiRecordNotFoundException(String errorDetail) {
		super(Status.Type.NOT_FOUND.getCode(), errorDetail);
	}
}
