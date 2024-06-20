package com.thesis.bankingservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.bankingservice.client.MailSmsClientREST;
import com.thesis.bankingservice.client.PaymentClientREST;
import com.thesis.bankingservice.client.RatingClientREST;
import com.thesis.bankingservice.model.*;
import com.thesis.bankingservice.model.UserDataModels.model.UserData;
import com.thesis.bankingservice.service.BankDBService;
import com.thesis.bankingservice.service.BankingServiceREST;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@Profile("rest")
@RestController
public class BankingControllerREST {
    private final ObjectMapper obj = new ObjectMapper();
    private final Logger logger = LogManager.getLogger(BankingControllerREST.class);
    private final BankingServiceREST bankingServiceREST;
    private final PaymentClientREST paymentClientREST;
    private final RatingClientREST ratingClientREST;

    private final MailSmsClientREST mailSmsClientREST;
    private final BankDBService dbService;

    public BankingControllerREST(BankDBService dbService, @Value("${ratingservice.rest.url}") String ratingServiceUrl, @Value("${paymentservice.rest.url}") String paymentServerUrl, @Value("${mailsmsservice.rest.url}") String mailServerUrl, WebClient.Builder webClientBuilder) {
        this.dbService = dbService;
        this.bankingServiceREST = new BankingServiceREST(dbService);
        this.paymentClientREST = new PaymentClientREST(paymentServerUrl, webClientBuilder);
        this.ratingClientREST = new RatingClientREST(ratingServiceUrl, webClientBuilder);
        this.mailSmsClientREST = new MailSmsClientREST(mailServerUrl, webClientBuilder);
    }

    @PostMapping("/create-practice")
    public ResponseEntity<String> createPracticeId(@RequestHeader(value = "Request-ID") String reqId, @RequestBody String reqBody) throws JsonMappingException, JsonProcessingException {
        String result;
        logger.info("REQUEST ID: {} INPUT: {}", reqId, reqBody);
        JsonNode customer = obj.readTree(reqBody);
        logger.info(reqBody);
        if (customer.has("vehicleInfo") && customer.has("financingInfo") && customer.has("personalData")) {
            Financing finInfo=obj.readValue(customer.get("financingInfo").toString(),Financing.class);
            Vehicle vehicleInfo=obj.readValue(customer.get("vehicleInfo").toString(),Vehicle.class);
            result = bankingServiceREST.createPractice(
                    customer.get("personalData").get("name").asText(),
                    customer.get("personalData").get("surname").asText(),
                    customer.get("personalData").get("email").asText(),
                    customer.get("personalData").get("name").asText(),
                    finInfo,
                    vehicleInfo);


            if (result != null) {
                return ResponseEntity.ok().body(result);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {

            return ResponseEntity.badRequest().build();

        }

    }


    @PostMapping("/additional-info")
    public ResponseEntity<String> addInfo(@RequestParam String practiceId, @RequestBody AdditionalInfo additionalInfo) throws JsonProcessingException {
        if (dbService.practiceExists(practiceId)) {
            logger.info(additionalInfo);
            if (additionalInfo!=null) {
                dbService.updatePractice(practiceId, additionalInfo);
                return ResponseEntity.ok().body("added");
            }
        } else {
            return ResponseEntity.badRequest().body("MISSING ADDITIONAL INFO!");
        }
        return ResponseEntity.badRequest().body("PRACTICE NOT FOUND!");
    }

    @PostMapping("/credit-card-payment")
    public ResponseEntity<String> ccPayment(String reqId, @RequestParam String practiceId, @RequestBody Card card) {
        if (dbService.practiceExists(practiceId)) {
            dbService.setPaymentMethod(practiceId, "card", card.toString());
            if (paymentClientREST.creditCardPayment(card)) {
                dbService.setPracticeToCompleted(practiceId);
                return ResponseEntity.ok().body("accepted");
            } else {
                return ResponseEntity.badRequest().body("refused");
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/bank-transfer-payment")
    public ResponseEntity<String> btPayment(@RequestParam String practiceId, @RequestBody Transfer transfer) {
        if (dbService.practiceExists(practiceId)) {
            dbService.setPaymentMethod(practiceId, "transfer", transfer.toString());
            if (paymentClientREST.bankTransferPayment(transfer)) {
                dbService.setPracticeToCompleted(practiceId);
                return ResponseEntity.ok().body("accepted");
            } else {
                return ResponseEntity.badRequest().body("refused");
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/personal-document")
    public ResponseEntity<String> personalDocumnet(@RequestParam String practiceId, @RequestBody PersonalDocument personalDocument) {
        if (dbService.practiceExists(practiceId)) {
            dbService.setPersonalDocument(practiceId, personalDocument);
            return ResponseEntity.ok().body("added");
        } else {
            return ResponseEntity.badRequest().body("Practice not found!");
        }
    }

    @PostMapping("/credit-document")
    public ResponseEntity<String> creditDocument(@RequestParam String practiceId, @RequestBody String creditDocument) {
        if (dbService.practiceExists(practiceId)) {
            dbService.setCreditDocument(practiceId, creditDocument);
            return ResponseEntity.ok().body("added");
        } else {
            return ResponseEntity.badRequest().body("Practice not found!");
        }
    }

    @PostMapping("/evaluate-practice")
    public ResponseEntity<PracticeEntity> evaluatePractice(@RequestParam String practiceId) throws JsonProcessingException {

        if (dbService.practiceExists(practiceId)) {

            PracticeEntity temp = dbService.getFullPractice(practiceId);
            PracticeEntity response = ratingClientREST.getPracticeEvaluation(temp);

            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/practice-overview")
    public ResponseEntity<PracticeEntity> practiceOverview(@RequestParam String practiceId) {
        if (dbService.practiceExists(practiceId)) {
            PracticeEntity result = mailSmsClientREST.practiceOverview(dbService.getFullPractice(practiceId));
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/practice-exists")
    public ResponseEntity<Boolean> practiceCheck(@RequestParam String practiceId) {
        return ResponseEntity.ok().body(dbService.practiceExists(practiceId));

    }

    @PostMapping("/set-user-data")
    public ResponseEntity<Boolean> handleUserData(@RequestParam String practiceId, @RequestBody UserData userData) throws JsonProcessingException {
        if (dbService.practiceExists(practiceId)) {
            PracticeEntity temp = dbService.getFullPractice(practiceId);
            String userDataJson = obj.writeValueAsString(userData);
            dbService.setUserData(practiceId, userData);
            return ResponseEntity.ok().body(true);
        } else {
            return ResponseEntity.badRequest().body(false);
        }
    }


}
