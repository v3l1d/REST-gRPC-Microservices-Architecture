package com.thesis.bankingservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.bankingservice.client.PaymentClientREST;
import com.thesis.bankingservice.client.RatingClientREST;
import com.thesis.bankingservice.model.Card;
import com.thesis.bankingservice.model.Transfer;
import com.thesis.bankingservice.service.BankDBService;
import com.thesis.bankingservice.service.BankingServiceREST;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Profile("rest")
@RestController
public class BankingControllerREST {
    private final ObjectMapper obj=new ObjectMapper();
    private final Logger logger=LogManager.getLogger(BankingControllerREST.class);
    private final BankingServiceREST bankingServiceREST;
    private final PaymentClientREST paymentClientREST;
    private final RatingClientREST ratingClientREST;
    private final BankDBService dbService;

 
    public BankingControllerREST(BankDBService dbService,@Value("${ratingservice.rest.url}") String ratingServiceUrl,@Value("${paymentservice.rest.url}")String paymentServerUrl){
        this.dbService=dbService;
        this.bankingServiceREST=new BankingServiceREST(dbService);
        this.paymentClientREST=new PaymentClientREST(paymentServerUrl);
        this.ratingClientREST=new RatingClientREST(ratingServiceUrl);
    }

    @PostMapping("/create-practice")
    public ResponseEntity<String> createPracticeId(@RequestBody String reqBody) throws JsonMappingException, JsonProcessingException{
        String result;
        JsonNode customer=obj.readTree(reqBody);
        logger.info(reqBody);
        logger.info(customer.has("personalData"));
        logger.info(customer.has("financingId"));
        if(customer.has("personalData") && customer.has("financingId") && customer.has("amount")){
            result=bankingServiceREST.createPractice(customer.get("personalData").get("name").asText(),customer.get("personalData").get("surname").asText(),customer.get("personalData").get("phone").asText(),customer.get("personalData").get("email").asText(), customer.get("financingId").asText(), customer.get("amount").asDouble());
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
    public ResponseEntity<String> ccPayment(@RequestParam  String practiceId, @RequestBody  Card card){
        if(dbService.practiceExists(practiceId)){
            if(paymentClientREST.creditCardPayment(card)){
                return ResponseEntity.ok().body("PAYMENT ACCEPTED!");
            }else{
                return ResponseEntity.badRequest().body("PAYMENT REFUSED!");
            }
        }else{
            return ResponseEntity.badRequest().body("PAYMENT REFUSED!");
        }
    }


    @PostMapping("/bank-transfer-payment")
    public ResponseEntity<String> btPayment(@RequestParam String practiceId, @RequestBody Transfer transfer){
        if(dbService.practiceExists(practiceId)){
            if(paymentClientREST.bankTransferPayment(transfer)){
            return  ResponseEntity.ok().body("PAYMENT ACCEPTED!");
            }else{
                return ResponseEntity.badRequest().body("PAYMENT REFUSED");
            }
        }else {
            return ResponseEntity.badRequest().build();
        }


    }
    @PostMapping("/evaluate-practice")
    public ResponseEntity<String> evaluatePractice(@RequestParam String practiceId) {
        if(dbService.practiceExists(practiceId)){
            String response=ratingClientREST.getPracticeEvaluation(practiceId);
            if(response.equals("GOOD PRACTICE!")){
                return ResponseEntity.ok().body("PRACTICE QUALITY: (8/10)");
        }else{
            return ResponseEntity.badRequest().body("ERROR IN EVALUATION");
        }
       
    }else{
        return ResponseEntity.badRequest().body("PRACTICE NOT FOUND!");
    }
}
    

}
