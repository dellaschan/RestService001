package com.example.restservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class MSMResponse {
	
	@JsonProperty("tradeId")
	private String tradeId;
	
	@JsonProperty("messageId")
	private String messageId;
	
	@JsonProperty("amount")
	private Double amount;
	
	@JsonProperty("currency")
	private String currency;
	
	@JsonProperty("valueDate")
	private String valueDate;
	
	@JsonProperty("supportingInformation")
	private String supportingInformation;

	@JsonProperty("payerParty")
	private Party payerParty;
	
	@JsonProperty("receiverParty")
	private Party receiverParty;

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
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

	public String getSupportingInformation() {
		return supportingInformation;
	}

	public void setSupportingInformation(String supportingInformation) {
		this.supportingInformation = supportingInformation;
	}

	public Party getPayerParty() {
		return payerParty;
	}

	public void setPayerParty(Party payerParty) {
		this.payerParty = payerParty;
	}

	public Party getReceiverParty() {
		return receiverParty;
	}

	public void setReceiverParty(Party receiverParty) {
		this.receiverParty = receiverParty;
	}

	@Override
	public String toString() {
		return "MSMTO [tradeId=" + tradeId + ", messageId=" + messageId + ", amount=" + amount + ", currency="
				+ currency + ", valueDate=" + valueDate + ", supportingInformation=" + supportingInformation
				+ ", payerParty=" + payerParty + ", receiverParty=" + receiverParty + "]";
	}
	
}
