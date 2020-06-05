package com.ibm.ms1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.ms1.dto.ConversionDTO;
import com.ibm.ms1.service.MS1Service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RefreshScope
@RestController
@Api(value = "Create, update and gets conversion factors based on country codes")
public class MS1Controller {

	private static Logger logger = LoggerFactory.getLogger(MS1Controller.class);

	@Autowired
	MS1Service ms1Service;

	@ApiOperation("Adds new country code and its conversion factor to DB.")
	@PostMapping("addConversionFactor")
	public ResponseEntity<ConversionDTO> addConversionFactor(@RequestBody ConversionDTO conversionDTO) {
		logger.info("Inside addConversionFactor Controller");
		return new ResponseEntity<ConversionDTO>(ms1Service.addFactor(conversionDTO), HttpStatus.CREATED);
	}

	@ApiOperation("Retrieves conversion factor based on input country code.")
	@GetMapping("getConversionFactor/{countryCode}")
	public ResponseEntity<Double> getConversionFactor(@PathVariable(value = "countryCode") String countryCode) {
		logger.info("Inside getConversionFactor Controller");
		ConversionDTO dto = ms1Service.getDetails(countryCode);
		if (null == dto.getCountryCode()) {
			logger.info("No Data Found");
			return new ResponseEntity<Double>(dto.getConversionFactor(), HttpStatus.NO_CONTENT);

		}
		return new ResponseEntity<Double>(dto.getConversionFactor(), HttpStatus.OK);
	}

	@ApiOperation("Updates if exists country code and its conversion factor to DB else adds new row.")
	@PutMapping("updateConversionFactor")
	public ResponseEntity<ConversionDTO> updateConversinFactor(@RequestBody ConversionDTO conversionDTO) {
		logger.info("Inside updateConversinFactor Controller");
		return new ResponseEntity<ConversionDTO>(ms1Service.updateDetails(conversionDTO), HttpStatus.OK);

	}
}
