package com.thesis.financialcalcservice;

import jakarta.persistence.Entity;
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
		/*  MailSmsClient client = new MailSmsClient("localhost",50053);
		   try {
            // Send SMS OTP

            String pass=client.sendMailOtp("spastalex@gmail.com");
            String sms=client.sendSmsOtp("39","12344");
            System.out.println(sms);
            Boolean res=client.verifyMail(pass);
            System.out.println(res);
        } finally {
            client.shutdown();
        } */ 


      
	}

}
