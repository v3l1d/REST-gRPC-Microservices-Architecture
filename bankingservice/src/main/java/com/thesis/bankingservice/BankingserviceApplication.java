package com.thesis.bankingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.thesis.bankingservice.repository.PracticeRepository;
import com.thesis.bankingservice.service.BankingServer;
import com.thesis.generated.Practice;

@SpringBootApplication
@ComponentScan(basePackages ={"com.thesis.bankingservice"})
@EnableJpaRepositories("com.thesis.bankingservice.repository")
@EntityScan("com.thesis.bankingservice.model")
public class BankingserviceApplication {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BankingserviceApplication.class, args);
	

	}

}
