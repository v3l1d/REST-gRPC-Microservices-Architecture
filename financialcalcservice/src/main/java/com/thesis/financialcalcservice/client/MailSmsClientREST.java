package com.thesis.financialcalcservice.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class MailSmsClientREST {
    private ObjectMapper obj= new ObjectMapper();
    private final Logger logger=LogManager.getLogger(MailSmsClientREST.class);
    private final WebClient webClient=WebClient.builder().baseUrl("http://localhost:9093").build();

    public MailSmsClientREST(){

    }

    public String getMailOtp(String email) {
        String otp;
        JsonNode requestBody = obj.createObjectNode()
                .put("email", email);
        String response = webClient.post()
                .uri("/get-mail-otp")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        logger.info("GETMAILOTP RESPONSE : {}", response);
        otp = response != null ? response : "";
        return otp;
    }
    

    public String getSmsOtp(String phone){
        String otp;
        JsonNode requestBody = obj.createObjectNode()
                .put("phone", phone);
        String response = webClient.post()
                .uri("/get-sms-otp")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        logger.info("GETSMSOTP RESPONSE : {}", response);
        otp = response != null ? response : "";
        return otp;
    }

    public boolean verifyMail(String otp){
        boolean result;
        JsonNode requestBody= obj.createObjectNode()
        .put("mailOtp", otp);
        
        String response=webClient.post()
        .uri("/verify-mail")
        .bodyValue(requestBody)
        .retrieve()
        .bodyToMono(String.class)
        .block();
        logger.info("MAIL VERIFICATION RESULT: {} " , response);
        if(response.equals("VERIFIED")){
            result=true;
        }else{
            result=false;
        }
        return result;
    }

    public boolean verifySms(String otp){
        boolean result;
        JsonNode requestBody=obj.createObjectNode()
            .put("smsOtp",otp);
            String response=webClient.post()
                .uri("/verify-sms")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
            logger.info("SMS VERIFICATION RESULT: {}",response);
            if(response.equals("VERIFIED")){
                result=true;
            }else{
                result=false;
            }
            return result;
    }
}