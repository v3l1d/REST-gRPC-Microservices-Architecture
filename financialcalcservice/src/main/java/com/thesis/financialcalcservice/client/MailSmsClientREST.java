package com.thesis.financialcalcservice.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;



@Profile("rest")
public class MailSmsClientREST {
    private ObjectMapper obj= new ObjectMapper();
    private final Logger logger=LogManager.getLogger(MailSmsClientREST.class);
    private final String MailSmsServiceUrl;
    private final WebClient webClient;

    public MailSmsClientREST(String mailSmsServiceUrl, WebClient.Builder webClientBuilder) {
        this.MailSmsServiceUrl = mailSmsServiceUrl;
        this.webClient = webClientBuilder.baseUrl(MailSmsServiceUrl).build();
    }

    public String getMailOtp(String mail,String reqId) {
        String otp;
        JsonNode requestBody = obj.createObjectNode()
                .put("mail", mail);
        String response = webClient.post()
                .uri("/get-mail-otp")
                .header("Request-ID", reqId)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        otp = response != null ? response : "";
        return otp;
    }
    

    public String getSmsOtp(String phone,String reqId){
        String otp;
        JsonNode requestBody = obj.createObjectNode()
                .put("phone", phone);
        String response = webClient.post()
                .uri("/get-sms-otp")
                .header("Request-ID", reqId)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        logger.info("REQUEST ID:{} INPUT:{} OUTPUT:{}",reqId,phone,response);
        otp = response != null ? response : "";
        return otp;
    }

    public boolean verifyMail(String otp,String reqId){
        boolean result;
        JsonNode requestBody= obj.createObjectNode()
        .put("mailOtp", otp);
        Boolean response=webClient.post()
        .uri("/verify-mail")
        .header("Request-ID", reqId)
        .bodyValue(requestBody)
        .retrieve()
        .bodyToMono(Boolean.class)
        .block();
        
        return response.equals(true);
    }

    public boolean verifySms(String otp,String reqId) {
        boolean result;
        JsonNode requestBody = obj.createObjectNode()
                .put("smsOtp", otp);
        Boolean response = webClient.post()
                .uri("/verify-sms")
                .header("Request-ID", reqId)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        return response.equals(true);
    }
}