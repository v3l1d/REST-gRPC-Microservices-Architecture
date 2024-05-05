package com.thesis.paymentservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Profile("rest")
public class PaymentController {
    private final ObjectMapper obj= new ObjectMapper();
    private final Logger logger=LogManager.getLogger();

    @PostMapping("/credit-card")
    public ResponseEntity<String> ccPayment(@RequestBody String card) throws JsonProcessingException {
        logger.info("REQUEST BODY:{}",card);
        JsonNode reqBody=obj.readTree(card);
        if(reqBody!=null){
            return ResponseEntity.ok().body("ACCEPTED");
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/bank-transfer")
    public ResponseEntity<String> btPayment(@RequestBody String transfer) throws JsonProcessingException {
        logger.info("REQUEST BODY:{}",transfer);
        JsonNode reqBody= obj.readTree(transfer);
        if(reqBody!=null){
            return ResponseEntity.ok().body("ACCEPTED");
        }else{
            return ResponseEntity.badRequest().build();
        }

    }
}
