package com.thesis.mailsmsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.thesis.mailsmsservice.Config.GrpcServer;

@SpringBootApplication
public class MailsmsserviceApplication {

	public static void main(String[] args) throws Exception{
		SpringApplication.run(MailsmsserviceApplication.class, args);
		GrpcServer srv= new GrpcServer();
		srv.start();
	}

}
