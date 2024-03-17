package com.thesis.financialcalcservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("com.thesis.financialcalcservice.repository")
@EntityScan("com.thesis.financialcalcservice.model")
public class FinancialcalcserviceApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(FinancialcalcserviceApplication.class, args);
      
	}

}
