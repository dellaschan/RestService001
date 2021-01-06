package com.example.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restservice.model.SSI;

public interface SSIRepository extends JpaRepository<SSI, Long> {
	
	SSI findBySsiCode(String ssiCode);
}
