package com.thesis.bankingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thesis.bankingservice.service.BankingServer;

@SpringBootApplication
public class BankingserviceApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(BankingserviceApplication.class, args);
		BankingServer srv= new BankingServer();
		

	}

}
