package com.thesis.mailsmsservice.Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.mailsmsservice.Service.VerifyServiceREST;

@Profile("rest")
@RestController
public class MailSMSContoller {
    private final ObjectMapper obj=new ObjectMapper();
    private final VerifyServiceREST verifyService = new VerifyServiceREST();
    private final Logger logger=LogManager.getLogger(MailSMSContoller.class);

    @PostMapping("/get-mail-otp")
    public ResponseEntity<String> generateOtp(@RequestBody String data) throws JsonMappingException, JsonProcessingException{
        JsonNode body=obj.readTree(data);
        logger.info("BODY REQUEST: {}",body);
        if(data!=null){
            String mailOtp=verifyService.generateMailOtp();
            return ResponseEntity.ok().body(mailOtp);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/get-sms-otp")
    public ResponseEntity<String> generteSmsOtp(@RequestBody String data) throws JsonMappingException, JsonProcessingException{
        JsonNode body=obj.readTree(data);
        logger.info("BODY REQUEST SMS: {} ",body);
        if(data!=null){
            String smsOtp=verifyService.generateSMSOtp();
            return ResponseEntity.ok().body(smsOtp);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/verify-mail")
    public ResponseEntity<String> verifyMailOtp(@RequestBody String mailOtp) throws JsonMappingException, JsonProcessingException{
        if(verifyService.verifyMailOtp(mailOtp)){
            return ResponseEntity.ok().body("VERIFIED");
        }else{
            return ResponseEntity.badRequest().body("WRONG PASSWORD");
        }
    }

    @PostMapping("/verify-sms")
    public ResponseEntity<String> verifySMSOtp(@RequestBody String smsOtp) throws JsonMappingException, JsonProcessingException{
        
        if(verifyService.verifySMSOtp(smsOtp)){
            return ResponseEntity.ok().body("VERIFIED");
        } else{
            return ResponseEntity.badRequest().body("WRONG PASSWORD!");
        }
    }
}
