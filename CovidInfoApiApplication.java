package com.covid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "covid.com")
public class CovidInfoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidInfoApiApplication.class, args);

	}

}