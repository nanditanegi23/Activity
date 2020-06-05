package com.ibm.ms1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.ms1.domain.Conversion;

@Repository
public interface MS1Repository extends JpaRepository<Conversion, String> {

	Conversion findByCountryCode(String countryCode);
}