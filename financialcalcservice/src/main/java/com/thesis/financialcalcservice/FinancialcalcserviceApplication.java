package com.thesis.financialcalcservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thesis.financialcalcservice.Service.MailSmsClient;



@SpringBootApplication
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
