package com.thesis.collectorservice.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thesis.collectorservice.client.BankingClientREST;
import com.thesis.collectorservice.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import com.thesis.collectorservice.model.UserDataModels.UserData;

@Profile("rest")
@RestController
public class DataCollectionControllerRest {

    private final Logger logger= LogManager.getLogger(DataCollectionControllerRest.class);
    private final BankingClientREST bankingClientREST;
    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;
    private static final String url="http://bankingservice:9091/practice-exists?practiceId=";


    public DataCollectionControllerRest(@Value("${bankingservice.rest.url}")String bankingHost, RestTemplate restTemplate, WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
        this.bankingClientREST = new BankingClientREST(bankingHost,webClientBuilder);
        this.restTemplate = restTemplate;
    }

    @PostMapping("/additional-info")
    public ResponseEntity<String> additionalInfo(@RequestParam String practiceId, @RequestBody AdditionalInfo additionalInfo){
        ResponseEntity<Boolean> verify=restTemplate.getForEntity(url.concat(practiceId),Boolean.class);
        if(verify.getBody().toString().equals("true")){
            logger.info("{}",additionalInfo);
            boolean res=bankingClientREST.insertAdditionalInfo(practiceId,additionalInfo);
            if(res) return ResponseEntity.ok().body("Additional information added!");
            else return ResponseEntity.badRequest().build();
        }else{
            return ResponseEntity.badRequest().body("Practice not found!");
        }
    }

    @PostMapping("/personal-document")
    public ResponseEntity<String> personalDocument(@RequestParam String practiceId, @RequestBody PersonalDocument personalDocument){
        boolean verify=bankingClientREST.practiceExists(practiceId);
        if(verify){
            boolean res=bankingClientREST.insertPersonalDocument(practiceId,personalDocument);
            if(res) return ResponseEntity.ok().body("Personal document added!");
            else return ResponseEntity.ok().body("Personal document adding failed!");
        }else {
            return ResponseEntity.badRequest().body("Practice not found!");
        }
    }

    @PostMapping("/credit-document")
    public ResponseEntity<String> creditDocument(@RequestParam String practiceId,@RequestBody String creditDocument){
        ResponseEntity<Boolean> verify=restTemplate.getForEntity(url.concat(practiceId),Boolean.class);
        if(verify.getBody().toString().equals("true")){
            boolean res=bankingClientREST.insertCreditDocument(practiceId,creditDocument);
            if(res) return ResponseEntity.ok().body("Credit document added!");
            else return ResponseEntity.ok().body("Credit document adding failed!");
        }else {
            return ResponseEntity.badRequest().body("Practice not found!");
        }
    }
    @PostMapping("/credit-card-payment")
    public ResponseEntity<String> ccPayment(@RequestParam String practiceId, @RequestBody Card card){
        ResponseEntity<Boolean> verify = restTemplate.getForEntity(url.concat(practiceId), Boolean.class);
        if (verify.getBody().toString().equals("true")){
            boolean res=bankingClientREST.insertCardInfo(practiceId,card);
            if(res){
                return ResponseEntity.ok().body("Payment succeeded!");
            }else {
                return ResponseEntity.badRequest().body("Payment failed!");
            }
        }
        return ResponseEntity.badRequest().body("Practice not found!");
    }

    @PostMapping("/bank-transfer-payment")
    public ResponseEntity<String>  btPayment(@RequestParam String practiceId, @RequestBody Transfer transfer){
        ResponseEntity<Boolean> verify=restTemplate.getForEntity(url.concat(practiceId),Boolean.class);
        if(verify.getBody().toString().equals("true")){
            boolean res=bankingClientREST.insertBtInfo(practiceId,transfer);
            if(res) return ResponseEntity.ok().body("Payment succeeded"); else return ResponseEntity.badRequest().body("Payment failed!");
        }

        return ResponseEntity.badRequest().body("Practice not found!");
    }

    @PostMapping("/evaluate-practice")
    public ResponseEntity<PracticeEntity> evaluatePractice(@RequestParam String practiceId) throws JsonProcessingException {
        ResponseEntity<Boolean> verify=restTemplate.getForEntity(url.concat(practiceId),Boolean.class);
        if(verify.getBody().toString().equals("true")) {
            PracticeEntity temp= bankingClientREST.sendToEvaluation(practiceId);
            return ResponseEntity.ok().body(temp);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/practice-overview")
    public ResponseEntity<PracticeEntity> practiceOverview(@RequestParam String practiceId){
        ResponseEntity<Boolean> verify=restTemplate.getForEntity(url.concat(practiceId),Boolean.class);
        if (verify.getBody().toString().equals("true")) {
            PracticeEntity result= bankingClientREST.practiceOverview(practiceId);
            if(result!=null){
                return ResponseEntity.ok().body(result);
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/user-data")
    public ResponseEntity<Boolean> setUserData(@RequestParam String practiceId,@RequestBody UserData userData) throws JsonProcessingException {
        Boolean res=bankingClientREST.setUserData(practiceId,userData);
        if(res) return ResponseEntity.ok().body(true);
        else return ResponseEntity.badRequest().body(false);
    }

}
