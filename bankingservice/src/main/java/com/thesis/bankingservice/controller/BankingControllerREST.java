package com.thesis.bankingservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.bankingservice.model.Card;
import com.thesis.bankingservice.model.Transfer;
import com.thesis.bankingservice.service.BankDBService;
import com.thesis.bankingservice.service.BankingServiceREST;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Profile("rest")
@RestController
public class BankingControllerREST {
    private final ObjectMapper obj=new ObjectMapper();
    private final Logger logger=LogManager.getLogger(BankingControllerREST.class);
    private final BankingServiceREST bankingServiceREST;
    private final BankDBService dbService;

    @Autowired
    public BankingControllerREST(BankDBService dbService){
        this.dbService=dbService;
        bankingServiceREST=new BankingServiceREST(dbService);
    }

    @PostMapping("/create-practice")
    public ResponseEntity<String> createPracticeId(@RequestBody String reqBody) throws JsonMappingException, JsonProcessingException{
        String result;
        JsonNode customer=obj.readTree(reqBody);
        logger.info(reqBody.toString());
        logger.info(customer.has("personalData"));
        logger.info(customer.has("financingId"));
        if(customer.has("personalData") && customer.has("financingId")){
            result=bankingServiceREST.createPractice(customer.get("personalData").get("name").asText(),customer.get("personalData").get("surname").asText(),customer.get("personalData").get("phone").asText(),customer.get("personalData").get("email").asText(), customer.get("financingId").asText(),0);
            if(result!=null){
                return ResponseEntity.ok().body(result);
            }else{
                return ResponseEntity.badRequest().build();
            }
         } else{
            
            return ResponseEntity.badRequest().build();

        }

    }

    @PostMapping("/credit-card-payment")
    public ResponseEntity<String> ccPayment(String practId, Card card){

        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/bank-transfer-payment")
    public ResponseEntity<String> btPayment(String practId, Transfer transfer){
        return  ResponseEntity.badRequest().build();

    }

}
