package com.ibm.ms1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MS1Application {

	public static void main(String[] args) {
		SpringApplication.run(MS1Application.class, args);
	}

}
