package com.example.restservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.dto.TradeRequest;
import com.example.restservice.exception.ApiBadRequestException;
import com.example.restservice.model.MSM;
import com.example.restservice.model.SSI;
import com.example.restservice.response.APIResponse;
import com.example.restservice.response.MSMResponse;
import com.example.restservice.response.Status;
import com.example.restservice.service.MSMService;
import com.example.restservice.service.SSIService;

@RestController
@RequestMapping("/api/v1")
public class MSMController extends BaseController{
    
	private static final Logger logger = LoggerFactory.getLogger(MSMController.class);
	
	@Autowired
    MSMService msmService;
    @Autowired
    SSIService ssiService;

    @GetMapping("/msm/{tradeId}")
    public ResponseEntity<APIResponse> getMSM(@PathVariable String tradeId) {
        
    	ResponseEntity<APIResponse> response;
    	try{
    		validateRequired(tradeId, "Trade ID is mandatory");
    		MSM msm = msmService.getMSMByTradeId(tradeId);
    		validateHasRecord(msm, "No record found for the Trade ID provided");
        	
        	MSMResponse msmResponse = msmService.packageMSMResponse(msm);
            response = new ResponseEntity<>(buildGenericResponse(new Status(Status.Type.OK), msmResponse), null, HttpStatus.OK);
    	}
    	catch (Exception e) {
			response = exceptionHandler(e);
		}
    	
    	logger.info("getMSM >>> " + response.getStatusCodeValue());
		return response;
    }
    
    @PostMapping("/msm")
    public ResponseEntity<APIResponse> addMSM(@RequestBody TradeRequest tradeRequest) {
    	
    	ResponseEntity<APIResponse> response;
    	
    	try{
    		validateTradeRequest(tradeRequest);
    		validateNoRecord(msmService.getMSMByTradeId(tradeRequest.getTradeId()), "Trade Id already exist");
    		
    		SSI ssi = ssiService.findBySsiCode(tradeRequest.getSsiCode());
        	if(ssi == null){
        		throw new ApiBadRequestException("Invalid SSI Code");
        	}
    		MSM msm = msmService.packageMSM(tradeRequest, ssi);
        	msm = msmService.addMSM(msm);
        	MSMResponse msmResponse = msmService.packageMSMResponse(msm);
        	
        	response = new ResponseEntity<>(buildGenericResponse(new Status(Status.Type.CREATED), msmResponse), null, HttpStatus.CREATED);
    	}
    	catch (Exception e) {
			response = exceptionHandler(e);
		}
    	
    	logger.info("addMSM >>> " + response.getStatusCodeValue());
		return response;
    }
}
