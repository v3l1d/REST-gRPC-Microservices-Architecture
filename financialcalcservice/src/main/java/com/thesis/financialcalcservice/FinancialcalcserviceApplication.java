package com.thesis.financialcalcservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thesis.financialcalcservice.Service.MailSmsClient;
import com.thesis.generated.Otp;
import com.thesis.generated.Sms;


@SpringBootApplication
public class FinancialcalcserviceApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(FinancialcalcserviceApplication.class, args);
		   MailSmsClient client = new MailSmsClient("172.24.72.73",50053);
		   try {
            // Send SMS OTP
            client.sendSmsOtp("39", "39545");
        } finally {
            client.shutdown();
        }


      
	}

}
