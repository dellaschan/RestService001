package com.example.restservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class TradeRequest {
	
	@JsonProperty("tradeId")
	private String tradeId;
	
	@JsonProperty("ssiCode")
	private String ssiCode;
	
	@JsonProperty("amount")
	private Double amount;
	
	@JsonProperty("currency")
	private String currency;
	
	@JsonProperty("valueDate")
	private String valueDate;
	
	public TradeRequest(){
		
	}
	
	public TradeRequest(String tradeId, String ssiCode, Double amount, String currency, String valueDate){
		this.tradeId = tradeId;
		this.ssiCode = ssiCode;
		this.amount = amount;
		this.currency = currency;
		this.valueDate = valueDate;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getSsiCode() {
		return ssiCode;
	}

	public void setSsiCode(String ssiCode) {
		this.ssiCode = ssiCode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getValueDate() {
		return valueDate;
	}

	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}

	@Override
	public String toString() {
		return "TradeRequest [tradeId=" + tradeId + ", ssiCode=" + ssiCode + ", amount=" + amount + ", currency="
				+ currency + ", valueDate=" + valueDate + "]";
	}
	
}
