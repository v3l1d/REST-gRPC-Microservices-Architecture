package com.thesis.bankingservice.controller;

import com.thesis.bankingservice.client.PaymentClient;
import com.thesis.bankingservice.model.Transfer;
import com.thesis.bankingservice.model.Card;
import com.thesis.bankingservice.service.BankDBService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class BankingController {
    private final BankDBService dbService;
    private final PaymentClient paymentClient= new PaymentClient("localhost",50055);
    private final Logger logger=LogManager.getLogger(BankingController.class);
    public BankingController(BankDBService dbService) {
        this.dbService = dbService;
    }

    @PostMapping("/credit-card-payment")
    public ResponseEntity<String> ccPayment(@RequestParam String PracticeId,@RequestBody Card card) {
        logger.info("PRACITCE ID:{} ", PracticeId);
        if (dbService.practiceExists(PracticeId)) {
            String response=paymentClient.paymentRequestCard(card);
            logger.info("RESPONSE IN CONTROLLER: {}",response);
            if(!response.isEmpty()){
                return ResponseEntity.ok().body(response);
            }else{
                return ResponseEntity.badRequest().body("PAYMENT REFUSED");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Practice not found!");
        }

    }


    @PostMapping("/bank-transfer-payment")
    public ResponseEntity<String> btPayment(@RequestBody String practId, Transfer transfer){
        if (dbService.practiceExists(practId)) {
            String response=paymentClient.paymentRequestBank(transfer);
            logger.info("RESPONSE IN CONTROLLER: {}",response);
            if(response.equals("ACCEPTED")){
                return ResponseEntity.ok().body("PAYMENT ACCEPTED!");
            }else{
                return ResponseEntity.badRequest().body("PAYMENT REFUSED");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Practice not found!");
        }
    }

}
