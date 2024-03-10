package com.thesis.mailsmsservice.Service;

import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.SecureRandom;
import java.util.Base64;

import org.apache.logging.log4j.LogManager;

@Profile("rest")
public class VerifyServiceREST {
    private String mailOtp;
    private String smsOtp;
     ObjectMapper obj=new ObjectMapper();
    @SuppressWarnings("unused")
    private final Logger logger=LogManager.getLogger(VerifyServiceREST.class);

    public VerifyServiceREST(){

    }

    public String generateMailOtp(){
        this.mailOtp=generateRandomString(5);
        logger.info("MAIL OTP IN SERVICE GENERATED:{}",mailOtp);
        return mailOtp;
    }


    public String generateSMSOtp(){
        this.smsOtp=generateRandomString(5);
        logger.info("Mail OTP IN SERVICE GENERATED:{}",smsOtp);
        return smsOtp;
    }

    public boolean verifyMailOtp(String otp) throws JsonMappingException, JsonProcessingException{
        JsonNode passwords=obj.readTree(otp);    
        if(passwords.has("mailOtp") && passwords.get("mailOtp").asText().equals(this.mailOtp)){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean verifySMSOtp(String otp) throws JsonMappingException, JsonProcessingException{
        JsonNode passwords=obj.readTree(otp);
        logger.info(otp);
        logger.info(passwords.get("smsOtp"));
        if(passwords.has("smsOtp") && passwords.get("smsOtp").asText().equals(this.smsOtp)){
            return true;
        }else{
            return false;
        }
    }

     public static String generateRandomString(int length) {

        SecureRandom random = new SecureRandom();

        byte[] randomBytes = new byte[length];

        random.nextBytes(randomBytes);

        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);

    }

}
