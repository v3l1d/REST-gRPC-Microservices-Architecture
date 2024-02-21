package com.thesis.paymentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.thesis.paymentservice.service.PaymentServer;
@SpringBootApplication
public class PaymentserviceApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PaymentserviceApplication.class, args);
		PaymentServer srv= new PaymentServer();
	}

}
