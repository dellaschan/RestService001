package com.example.restservice.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.restservice.dto.TradeRequest;
import com.example.restservice.exception.ApiBadRequestException;
import com.example.restservice.exception.ApiException;
import com.example.restservice.exception.ApiRecordNotFoundException;
import com.example.restservice.response.APIResponse;
import com.example.restservice.response.Status;

public class BaseController {

	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	protected ResponseEntity<APIResponse> exceptionHandler(Exception e) {

		APIResponse resp = new APIResponse();
		
		if (e instanceof ApiException) {
			ApiException apie = (ApiException) e;
			Status.Type type = Status.Type.fromCode(apie.getCode());
			Status status = new Status(type, apie.getErrorDetail());
			resp.setStatus(status);
			return ResponseEntity.status(status.getStatusCode()).body(resp);
		}
		else {
			logger.error("Exception: " + e.getMessage(), e);
			Status status = new Status(Status.Type.INTERNAL_SERVER_ERROR, e.getMessage());
			resp.setStatus(status);
			return ResponseEntity.status(status.getStatusCode()).body(resp);
		}
	}
	
	public static APIResponse buildGenericResponse(Status status, Object data) {

		APIResponse resp = new APIResponse();
		resp.setStatus(status);
		resp.setData(data);
		return resp;
	}
	
	protected void validateRequired(Object param, String message) throws ApiBadRequestException {
		if (param == null) {
			throw new ApiBadRequestException(message);
		}
		if (param instanceof String[]) {
			String[] arr = (String[]) param;
			if (arr.length == 0) {
				throw new ApiBadRequestException(message);
			}
		}
		if (StringUtils.isEmpty(param.toString())) {
			throw new ApiBadRequestException(message);
		}
	}
	
	protected void validateHasRecord(Object record, String message) throws ApiRecordNotFoundException {
		if (record == null) {
			throw new ApiRecordNotFoundException((message));
		}
		if (record instanceof String[]) {
			String[] arr = (String[]) record;
			if (arr.length == 0) {
				throw new ApiRecordNotFoundException(message);
			}
		}
		if (record instanceof Collection<?> && CollectionUtils.isEmpty((Collection<?>) record)) {
			throw new ApiRecordNotFoundException(message);
		}
	}
	
	protected void validateNoRecord(Object record, String message) throws ApiBadRequestException {
		if (record == null) { return; }
		if (record instanceof Collection<?> &&
			CollectionUtils.isEmpty((Collection<?>) record)) { 
			return;
		}
		throw new ApiBadRequestException(message);
	}
	
	protected void validateTradeRequest(TradeRequest tradeRequest) throws ApiBadRequestException{
    	validateRequired(tradeRequest, "Request Body is mandatory");
    	validateRequired(tradeRequest.getTradeId(), "Trade ID is mandatory field");
		validateRequired(tradeRequest.getSsiCode(), "SSI Code is mandatory field");
		validateRequired(tradeRequest.getAmount(), "Amount is mandatory field");
		validateRequired(tradeRequest.getCurrency(), "Currency is mandatory field");
		validateRequired(tradeRequest.getValueDate(), "Value Date is mandatory field");
		validateDateFormat(tradeRequest.getValueDate(), "Value Date");
    }
    
    protected void validateDateFormat(String dateValue, String fieldName) throws ApiBadRequestException{
    	try {
            new SimpleDateFormat("ddMMyyyy").parse(dateValue);
        } catch (Exception e) {
        	logger.error("validateDateFormat: " , e);
        	throw new ApiBadRequestException("Invalid date format -> " + fieldName);
        }
    }
}
