package com.example.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.restservice.model.MSM;

public interface MSMRepository extends JpaRepository<MSM, Long> {
 
	MSM findByTradeId(String tradeId);
}
