package com.thesis.bankingservice.controller;

import com.thesis.bankingservice.model.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class BankingController {
    @GetMapping("/payment")
    public ResponseEntity<String> Payment(@RequestBody Payment paymentData) {
        ResponseEntity<String> result;

        return  ResponseEntity.badRequest().build();
    }
}