package com.ibm.ms1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.ms1.domain.Conversion;
import com.ibm.ms1.dto.ConversionDTO;
import com.ibm.ms1.dto.ConversionMApper;
import com.ibm.ms1.repository.MS1Repository;

@Service
public class MS1Service {

	@Autowired
	MS1Repository repo;

	public ConversionDTO addFactor(ConversionDTO dto) {
		// TODO Auto-generated method stub

		ConversionMApper mapper = new ConversionMApper();
		Conversion conversion = mapper.convertConversionDTOToConversion(dto);
		Conversion convert = repo.save(conversion);
		return mapper.convertConversionToConversionDTO(convert);

	}

	public ConversionDTO getDetails(String countryCode) {
		// TODO Auto-generated method stub

		Conversion conversion = repo.findByCountryCode(countryCode);
		ConversionMApper mapper = new ConversionMApper();
		return mapper.convertConversionToConversionDTO(conversion);

	}


	public ConversionDTO updateDetails(ConversionDTO conversionDTO) {
		// TODO Auto-generated method stub

		Conversion conversion = repo.findByCountryCode(conversionDTO.getCountryCode());
		ConversionDTO dto = null;
		if (!(conversion == null)) {

			ConversionMApper mapper = new ConversionMApper();
			Conversion conversionMap = mapper.convertConversionDTOToConversion(conversionDTO);
			Conversion convert = repo.save(conversionMap);
			dto = mapper.convertConversionToConversionDTO(convert);
		} else {
			dto = addFactor(conversionDTO);
		}

		return dto;
	}
}
