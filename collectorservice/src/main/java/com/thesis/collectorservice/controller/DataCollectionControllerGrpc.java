package com.thesis.collectorservice.controller;


import brave.grpc.GrpcTracing;
import com.thesis.collectorservice.client.BankingClientGRPC;
import com.thesis.collectorservice.model.AdditionalInfo;
import com.thesis.collectorservice.model.Card;
import com.thesis.collectorservice.model.Transfer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
@Profile("grpc")
@RestController
public class DataCollectionControllerGrpc {
    private final Logger logger= LogManager.getLogger(DataCollectionControllerGrpc.class);
    private final BankingClientGRPC bankingClientGRPC;
    private final RestTemplate restTemplate;
    private static final String url = "http://localhost:9091/practice-exists?practiceId=";


    public DataCollectionControllerGrpc(@Value("${bankingservice.grpc.url}") String bankingHost, GrpcTracing grpcTracing, RestTemplate restTemplate){
        this.restTemplate = restTemplate;
        this.bankingClientGRPC=new BankingClientGRPC(bankingHost,grpcTracing);
    }
    @PostMapping("/additional-info")
    public ResponseEntity<String> additionalInfo(@RequestParam String practiceId,@RequestBody AdditionalInfo additionalInfo){
        ResponseEntity<Boolean> response = restTemplate.getForEntity(url.concat(practiceId), Boolean.class);
        if (response.getBody().toString().equals("true")){
            boolean res= bankingClientGRPC.insertAdditionalInfo(practiceId,additionalInfo);
            if(res){
                return  ResponseEntity.ok().body("Information addition completed");
            }else{
                return ResponseEntity.badRequest().body("Failed additional information adding!");
            }
        } else {

            return ResponseEntity.badRequest().build();

        }
}

    @PostMapping("/documents-upload")
    public ResponseEntity<String>  documentsUpload(){
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/credit-card-payment")
    public ResponseEntity<String> ccPayment(@RequestParam String practiceId, @RequestBody Card card){
        ResponseEntity<Boolean> verify = restTemplate.getForEntity(url.concat(practiceId), Boolean.class);
        if (verify.getBody().toString().equals("true")){
            boolean res=bankingClientGRPC.insertCardInfo(practiceId,card);
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
            boolean res=bankingClientGRPC.insertBtInfo(practiceId,transfer);
            if(res) return ResponseEntity.ok().body("Payment succeeded"); else return ResponseEntity.badRequest().body("Payment failed!");
        }

        return ResponseEntity.badRequest().body("Practice not found!");
    }

    @PostMapping("/evaluate-practice")
    public ResponseEntity<String> evaluatePractice(@RequestParam String practiceId) {
        ResponseEntity<Boolean> verify = restTemplate.getForEntity(url.concat(practiceId), Boolean.class);
        if (verify.getBody().toString().equals("true")) {
            String response = bankingClientGRPC.sendToEvaluation(practiceId);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().body("Practice not found!");
        }
    }



}
