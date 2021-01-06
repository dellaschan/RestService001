package com.example.restservice.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_NULL)
public class Party {
	
	@JsonProperty("accountNumber")
	private String accountNumber;
	
	@JsonProperty("bankCode")
	private String bankCode;

	public Party(String accountNumber, String bankCode){
		this.accountNumber = accountNumber;
		this.bankCode = bankCode;
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	@Override
	public String toString() {
		return "Party [accountNumber=" + accountNumber + ", bankCode=" + bankCode + "]";
	}
	
}
