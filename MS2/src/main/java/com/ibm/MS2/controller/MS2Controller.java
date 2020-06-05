package com.ibm.MS2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.MS2.service.MS2Service;

import io.swagger.annotations.ApiOperation;


@RefreshScope
@RestController
public class MS2Controller {
	
	@Autowired
	MS2Service service;
//	private static Logger logger= LoggerFactory.getLogger(MS2Controller.class);
	
	@ApiOperation("Calculates the new currency according to the country code.")
	@GetMapping("convertCurrency/{countrycode}/{amount}")
	public ResponseEntity<?> getAmount(@PathVariable(value="countrycode") String countryCode, @PathVariable(value="amount") Double amount) {
//		logger.info("Inside TaxController:"+name);
		return ResponseEntity.ok().body(service.calculateCurrency(countryCode,amount));
//		return null;
	}

}