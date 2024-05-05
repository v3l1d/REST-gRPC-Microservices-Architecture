package com.thesis.collectorservice.controller;


import com.thesis.collectorservice.client.BankingClientREST;
import com.thesis.collectorservice.model.AdditionalInfo;
import com.thesis.collectorservice.model.Card;
import com.thesis.collectorservice.model.Transfer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Profile("rest")
@RestController
public class DataCollectionControllerRest {

    private final Logger logger= LogManager.getLogger(DataCollectionControllerRest.class);
    private final BankingClientREST bankingClientREST;
    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;
    private static final String url="http://localhost:9091/practice-exists?practiceId=";


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
    public ResponseEntity<String> evaluatePractice(@RequestParam String practiceId) {
        ResponseEntity<Boolean> verify = restTemplate.getForEntity(url.concat(practiceId), Boolean.class);
        if (verify.getBody().toString().equals("true")) {
            String response = bankingClientREST.sendToEvaluation(practiceId);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().body("Practice not found!");
        }
    }

}
