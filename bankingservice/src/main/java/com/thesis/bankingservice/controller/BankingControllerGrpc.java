package com.thesis.bankingservice.controller;

import brave.grpc.GrpcTracing;
import com.thesis.bankingservice.client.PaymentClientGRPC;
import com.thesis.bankingservice.client.RatingClientGRPC;
import com.thesis.bankingservice.model.Transfer;
import com.thesis.bankingservice.model.Card;
import com.thesis.bankingservice.service.BankDBService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Profile("grpc")
@RestController
public class BankingControllerGrpc {
    @SuppressWarnings("unused")
    private final String paymentServerGrpcUrl;
    @SuppressWarnings("unused")
    private final String ratingServerGrpcUrl;
    private final BankDBService dbService;
    private final PaymentClientGRPC paymentClientGRPC;
    private final RatingClientGRPC ratingClientGRPC;
    private final Logger logger = LogManager.getLogger(BankingControllerGrpc.class);

    public BankingControllerGrpc(@Value("${paymentservice.grpc.url}") String paymentServerGrpcUrl,
                                 @Value("${ratingservice.grpc.url}") String ratingServerGrpcUrl,
                                 BankDBService dbService, GrpcTracing grpcTracing) {
        this.paymentServerGrpcUrl = paymentServerGrpcUrl;
        this.ratingServerGrpcUrl=ratingServerGrpcUrl;
        this.dbService = dbService;
        this.ratingClientGRPC=new RatingClientGRPC(ratingServerGrpcUrl,grpcTracing);
        this.paymentClientGRPC = new PaymentClientGRPC(paymentServerGrpcUrl,grpcTracing);
    }


    @PostMapping("/credit-card-payment")
    public ResponseEntity<String> ccPayment(@RequestHeader(value="X-Request-ID") String reqId,@RequestParam String practiceId,@RequestBody Card card) {
        logger.info("PRACITCE ID:{} ", practiceId);
        logger.info("CARD:{}", card);
        if (dbService.practiceExists(practiceId)) {
            String response= paymentClientGRPC.paymentRequestCard(card,reqId);
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
    public ResponseEntity<String> btPayment(@RequestHeader(value="X-Request-ID") String reqId,@RequestParam String practiceId,@RequestBody Transfer transfer){
      
        if (dbService.practiceExists(practiceId)) {
            String response= paymentClientGRPC.paymentRequestBank(transfer,reqId);
            logger.info("RESPONSE IN CONTROLLER: {}",response);
            if(!response.isEmpty()){
                return ResponseEntity.ok().body("PAYMENT ACCEPTED with id:" +response);
            }else{
                return ResponseEntity.badRequest().body("PAYMENT REFUSED");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Practice not found!");
        }
    }

    @PostMapping("/evaluate-practice")
    public ResponseEntity<String> evaluatePractice(@RequestHeader(value="X-Request-ID") String reqId,@RequestParam String practiceId){
        logger.info("Request ID: {}", reqId);
        if(dbService.practiceExists(practiceId)){
                String response=ratingClientGRPC.getPracticeEvaluation(practiceId,reqId);
            if(response.equals("GOOD PRACTICE!")){

                return ResponseEntity.ok("PRACTICE QUALITY: (8/10)");
       
            }else{
                return ResponseEntity.ok().body("ERROR IN EVALUATION");
            }
         }else{
            return ResponseEntity.badRequest().body("PRACTICE NOT FOUND!");
        }

    }

}
