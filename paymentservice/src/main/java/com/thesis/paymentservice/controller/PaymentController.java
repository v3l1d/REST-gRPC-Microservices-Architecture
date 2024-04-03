package com.thesis.paymentservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @PostMapping("/credit-card")
    public ResponseEntity<String> ccPayment(@RequestHeader(value="Request-ID") String reqId,@RequestBody String card) throws JsonProcessingException {
        JsonNode reqBody=obj.readTree(card);
        if(reqBody!=null){
            return ResponseEntity.ok().body("ACCEPTED");
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/bank-transfer")
    public ResponseEntity<String> btPayment(@RequestHeader(value="Request-ID") String reqId,@RequestBody String transfer) throws JsonProcessingException {
        JsonNode reqBody= obj.readTree(transfer);
        if(reqBody!=null){
            return ResponseEntity.ok().body("ACCEPTED");
        }else{
            return ResponseEntity.badRequest().build();
        }

    }
}
