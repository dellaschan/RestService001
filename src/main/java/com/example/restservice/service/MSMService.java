package com.example.restservice.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.restservice.dto.TradeRequest;
import com.example.restservice.model.MSM;
import com.example.restservice.model.SSI;
import com.example.restservice.repository.MSMRepository;
import com.example.restservice.response.MSMResponse;
import com.example.restservice.response.Party;
import com.example.restservice.util.RestUtils;
 
@Service
@Transactional
public class MSMService {
 
    @Autowired
    private MSMRepository msmRepository;
     
    public MSM addMSM(MSM msm) {
    	if(msm != null && StringUtils.isEmpty(msm.getMessageId())){
    		msm.setMessageId(RestUtils.generateUUID());
    	}
    	return msmRepository.save(msm);
    }
     
    public MSM getMSMByTradeId(String tradeId) {
        return msmRepository.findByTradeId(tradeId);
    }
    
    public MSM packageMSM(TradeRequest tradeRequest, SSI ssi){
    	MSM msm = new MSM();
    	msm.setTradeId(tradeRequest.getTradeId());
    	msm.setCurrency(tradeRequest.getCurrency());
    	msm.setValueDate(tradeRequest.getValueDate());
        msm.setAmount(tradeRequest.getAmount());
    	msm.setSsi(ssi);
    	return msm;
    }
    
    public MSMResponse packageMSMResponse(MSM msm){
    	MSMResponse msmResponse = new MSMResponse();
    	msmResponse.setTradeId(msm.getTradeId());
    	msmResponse.setCurrency(msm.getCurrency());
    	msmResponse.setValueDate(msm.getValueDate());
    	msmResponse.setAmount(msm.getAmount());
    	
    	SSI ssi = msm.getSsi();
    	Party payerParty = new Party(ssi.getPayerAccountNumber(), ssi.getPayerBank());
    	Party receiverParty = new Party(ssi.getReceiverAccountNumber(), ssi.getReceiverBank());
    	msmResponse.setPayerParty(payerParty);
    	msmResponse.setReceiverParty(receiverParty);
    	msmResponse.setSupportingInformation(ssi.getSupportingInformation());
    	
    	return msmResponse;
    }
    
}