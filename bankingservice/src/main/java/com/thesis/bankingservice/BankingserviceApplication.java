package com.thesis.bankingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.query.Procedure;


@SpringBootApplication
@EnableJpaRepositories("com.thesis.bankingservice.repository")
@EntityScan("com.thesis.bankingservice.model")
public class BankingserviceApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BankingserviceApplication.class, args);
	

	}

}
