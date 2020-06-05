package com.ibm.MS2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ibm.MS2.client.RestClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class MS2Service {
	
final RestClient restClient;

private static Logger logger = LoggerFactory.getLogger(MS2Service.class);
	
	@Autowired
	public MS2Service(RestClient restClient) {
		this.restClient=restClient;
	}
	

	@HystrixCommand(fallbackMethod="conversionFactorFallback")
	public Double calculateCurrency(String currency, Double amount) {
		// TODO Auto-generated method stub
		logger.info("Inside getAmount Service");
		Double conversionFactor=0.0;
		logger.info("Making call to MS1");
		ResponseEntity<Double> dto= restClient.getConversionFactor(currency);
		if(HttpStatus.OK.equals(dto.getStatusCode())) {
			logger.info("MS1 Call success status: "+ dto.getStatusCode());
			conversionFactor= dto.getBody();
		}
		logger.info("MS1 Call status: "+ dto.getStatusCode());
		
		return amount*conversionFactor;
	}

	
public Double conversionFactorFallback(String currency, Double amount) {
	logger.info("MS1 Call Timeout. Inside Fallback.");
	return 5.0;
	}
}
