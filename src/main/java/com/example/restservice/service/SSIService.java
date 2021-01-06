package com.example.restservice.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.restservice.model.SSI;
import com.example.restservice.repository.SSIRepository;
 
@Service
@Transactional
public class SSIService {
 
    @Autowired
    private SSIRepository ssiRepository;
    
    public void save(SSI ssi) {
    	ssiRepository.save(ssi);
    }
    
    public SSI findBySsiCode(String ssiCode) {
        return ssiRepository.findBySsiCode(ssiCode);
    }
}