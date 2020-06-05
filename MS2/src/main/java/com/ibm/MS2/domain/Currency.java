package com.ibm.MS2.domain;

import javax.persistence.Entity;

@Entity
public class Currency {

	private String countryCode;
	
	private Double Amount;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Double getAmount() {
		return Amount;
	}

	public void setAmount(Double amount) {
		Amount = amount;
	}
}
