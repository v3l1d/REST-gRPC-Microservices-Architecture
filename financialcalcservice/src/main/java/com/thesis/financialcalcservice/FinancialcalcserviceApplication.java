package com.thesis.financialcalcservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class FinancialcalcserviceApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(FinancialcalcserviceApplication.class, args);
		/**  MailSmsClient client = new MailSmsClient("172.24.72.73",50053);
		   try {
            // Send SMS OTP
            client.sendMailOtp("spastalex@gmail.com");
        } finally {
            client.shutdown();
        } */


      
	}

}
