package com.example.restservice.exception;

import com.example.restservice.response.Status;

public class ApiBadRequestException extends ApiException {
	private static final long serialVersionUID = 1L;

	public ApiBadRequestException(String errorDetail) {
		super(Status.Type.BAD_REQUEST.getCode(), errorDetail);
	}
}
