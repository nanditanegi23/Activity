package com.ibm.ms1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.ms1.domain.Conversion;
import com.ibm.ms1.dto.ConversionDTO;
import com.ibm.ms1.dto.ConversionMApper;
import com.ibm.ms1.repository.MS1Repository;

@Service
public class MS1Service {
	
	private static Logger logger= LoggerFactory.getLogger(MS1Service.class);

	@Autowired
	MS1Repository repo;

	public ConversionDTO addFactor(ConversionDTO dto) {
		// TODO Auto-generated method stub
		 logger.info("Inside addConversionFactor Service");
		ConversionMApper mapper = new ConversionMApper();
		Conversion conversion = mapper.convertConversionDTOToConversion(dto);
		Conversion convert = repo.save(conversion);
		 logger.info("Data Saved from addConversionFactor Service");
		return mapper.convertConversionToConversionDTO(convert);

	}

	public ConversionDTO getDetails(String countryCode) {
		// TODO Auto-generated method stub
		 logger.info("Inside getConversionFactor Service");
		Conversion conversion = repo.findByCountryCode(countryCode);
		ConversionMApper mapper = new ConversionMApper();
		return mapper.convertConversionToConversionDTO(conversion);

	}


	public ConversionDTO updateDetails(ConversionDTO conversionDTO) {
		// TODO Auto-generated method stub
		 logger.info("Inside updateConversinFactor Service");
		Conversion conversion = repo.findByCountryCode(conversionDTO.getCountryCode());
		ConversionDTO dto = null;
		if (!(conversion == null)) {

			ConversionMApper mapper = new ConversionMApper();
			Conversion conversionMap = mapper.convertConversionDTOToConversion(conversionDTO);
			Conversion convert = repo.save(conversionMap);
			 logger.info("Data Save from updateConversinFactor Service");
			dto = mapper.convertConversionToConversionDTO(convert);
		} else {
			logger.info("Data not found. Adding new data to DB.");
			dto = addFactor(conversionDTO);
		}

		return dto;
	}
}
