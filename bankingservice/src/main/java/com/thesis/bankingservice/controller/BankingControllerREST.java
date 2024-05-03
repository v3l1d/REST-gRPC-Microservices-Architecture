package com.thesis.bankingservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.bankingservice.client.PaymentClientREST;
import com.thesis.bankingservice.client.RatingClientREST;
import com.thesis.bankingservice.model.*;
import com.thesis.bankingservice.service.BankDBService;
import com.thesis.bankingservice.service.BankingServiceREST;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;


import static com.thesis.bankingservice.service.PracticeEntityJsonConverter.practiceEntityToJson;

@Profile("rest")
@RestController
public class BankingControllerREST {
    private final ObjectMapper obj=new ObjectMapper();
    private final Logger logger=LogManager.getLogger(BankingControllerREST.class);
    private final BankingServiceREST bankingServiceREST;
    private final PaymentClientREST paymentClientREST;
    private final RatingClientREST ratingClientREST;
    private final BankDBService dbService;
    private final ObjectMapper objectMapper= new ObjectMapper();
 
    public BankingControllerREST(BankDBService dbService,@Value("${ratingservice.rest.url}") String ratingServiceUrl,@Value("${paymentservice.rest.url}")String paymentServerUrl,WebClient.Builder webClientBuilder){
        this.dbService=dbService;
        this.bankingServiceREST=new BankingServiceREST(dbService);
        this.paymentClientREST=new PaymentClientREST(paymentServerUrl, webClientBuilder);
        this.ratingClientREST=new RatingClientREST(ratingServiceUrl, webClientBuilder);
    }

    @PostMapping("/create-practice")
    public ResponseEntity<String> createPracticeId(@RequestHeader(value="Request-ID") String reqId,@RequestBody String reqBody) throws JsonMappingException, JsonProcessingException{
        String result;
        logger.info("REQUEST ID: {} INPUT: {}",reqId,reqBody);
        JsonNode customer=obj.readTree(reqBody);
        logger.info(reqBody);
        if(customer.has("vehicleInfo") && customer.has("financingInfo") && customer.has("personalData")){
            result=bankingServiceREST.createPractice(
                    customer.get("personalData").get("name").asText(),
                    customer.get("personalData").get("surname").asText(),
                    customer.get("personalData").get("email").asText(),
                    customer.get("personalData").get("name").asText(),
                    customer.get("financingInfo").toString(),
                    customer.get("vehicleInfo").toString());


            if(result!=null){
                return ResponseEntity.ok().body(result);
            }else{
                return ResponseEntity.badRequest().build();
            }
         } else{
            
            return ResponseEntity.badRequest().build();

        }

    }

    @PostMapping("/complete-practice")
    public ResponseEntity<String> completePractice(@RequestHeader(value="X-Request-ID") String reqId, @RequestParam String practiceId, @RequestBody AdditionalInfo additionalInfo) throws JsonProcessingException {
        if (dbService.practiceExists(practiceId)) {
            logger.info(additionalInfo);
            if (additionalInfo.isValid()) {
                dbService.updatePractice(practiceId,additionalInfo.toString());
                return ResponseEntity.ok().body(additionalInfo.toString());
            }
        }else{
            return ResponseEntity.badRequest().body("MISSING ADDITIONAL INFO!");
        }
        return ResponseEntity.badRequest().body("PRACTICE NOT FOUND!");
    }
    @PostMapping("/credit-card-payment")
    public ResponseEntity<String> ccPayment(@RequestHeader(value="X-Request-ID") String reqId,@RequestParam  String practiceId, @RequestBody  Card card){
        if(dbService.practiceExists(practiceId)){
            dbService.setPaymentMethod(practiceId,"card",card.toString());
            if(paymentClientREST.creditCardPayment(card,reqId)){
                dbService.setPracticeToCompleted(practiceId);
                return ResponseEntity.ok().body("PAYMENT ACCEPTED!");
            }else{
                return ResponseEntity.badRequest().body("PAYMENT REFUSED!");
            }
        }else{
            return ResponseEntity.badRequest().body("PAYMENT REFUSED!");
        }
    }



    @PostMapping("/bank-transfer-payment")
    public ResponseEntity<String> btPayment(@RequestHeader(value="X-Request-ID") String reqId,@RequestParam String practiceId, @RequestBody Transfer transfer){
        if(dbService.practiceExists(practiceId)){
            dbService.setPaymentMethod(practiceId,"transfer",transfer.toString());
            if(paymentClientREST.bankTransferPayment(transfer,reqId)){
                dbService.setPracticeToCompleted(practiceId);
            return  ResponseEntity.ok().body("PAYMENT ACCEPTED!");
            }else{
                return ResponseEntity.badRequest().body("PAYMENT REFUSED");
            }
        }else {
            return ResponseEntity.badRequest().build();
        }


    }
    @PostMapping("/evaluate-practice")
    public ResponseEntity<String> evaluatePractice(@RequestHeader(value="X-Request-ID") String reqId,@RequestParam String practiceId) throws JsonProcessingException {
        if(dbService.practiceExists(practiceId)){
            String practiceJson=practiceEntityToJson(dbService.getFullPractice(practiceId));
            logger.info(practiceJson);
            String response=ratingClientREST.getPracticeEvaluation(practiceJson, reqId);
            if(!response.isEmpty()){
                return ResponseEntity.ok().body(response);
        }else{
            return ResponseEntity.badRequest().body("ERROR IN EVALUATION");
        }
       
    }else{
        return ResponseEntity.badRequest().body("PRACTICE NOT FOUND!");
    }
}
    

}
