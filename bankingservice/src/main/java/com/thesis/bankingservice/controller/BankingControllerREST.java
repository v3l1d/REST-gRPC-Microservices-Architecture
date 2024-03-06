package com.thesis.bankingservice.controller;

import com.thesis.bankingservice.model.Card;
import com.thesis.bankingservice.model.Transfer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BankingControllerREST {

    @PostMapping("/credit-card-payment")
    public ResponseEntity<String> ccPayment(String practId, Card card){

        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/bank-transfer-payment")
    public ResponseEntity<String> btPayment(String practId, Transfer transfer){
        return  ResponseEntity.badRequest().build();

    }

}
