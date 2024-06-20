package com.thesis.collectorservice.controller;


import brave.grpc.GrpcTracing;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.thesis.collectorservice.client.BankingClientGRPC;
import com.thesis.collectorservice.model.*;
import com.thesis.generated.*;
import com.thesis.generated.UserData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
@Profile("grpc")
@RestController
public class DataCollectionControllerGrpc {
    private final Logger logger= LogManager.getLogger(DataCollectionControllerGrpc.class);
    private final BankingClientGRPC bankingClientGRPC;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public DataCollectionControllerGrpc(@Value("${bankingservice.grpc.url}") String bankingHost, GrpcTracing grpcTracing, RestTemplate restTemplate){
        this.bankingClientGRPC=new BankingClientGRPC(bankingHost,grpcTracing);
    }
    @PostMapping("/additional-info")
    public ResponseEntity<String> additionalInfo(@RequestParam String practiceId,@RequestBody com.thesis.collectorservice.model.AdditionalInfo additionalInfo){
        boolean check= bankingClientGRPC.practiceExists(practiceId);
         if (check){
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

    @PostMapping("/personal-document")
    public ResponseEntity<String>  personalDocument(@RequestParam String practiceId, @RequestBody com.thesis.collectorservice.model.PersonalDocument personalDocument){
        boolean check= bankingClientGRPC.practiceExists(practiceId);
        if (check){
            boolean res=bankingClientGRPC.personalDocument(practiceId,personalDocument);
            if (res) return ResponseEntity.ok().body("Personal document added!");
            else return  ResponseEntity.badRequest().body("Personal document adding failed!");
        }else{
            return ResponseEntity.badRequest().body("Practice not found!");

        }
    }
    @PostMapping("/credit-document")
    public ResponseEntity<String>  creditDocument(@RequestParam String practiceId, @RequestBody String creditDocument){
        boolean check= bankingClientGRPC.practiceExists(practiceId);
        if (check){
            boolean res=bankingClientGRPC.creditDocument(practiceId,creditDocument);
            if (res) return ResponseEntity.ok().body("Personal document added!");
            else return  ResponseEntity.badRequest().body("Personal document adding failed!");
        }else{
            return ResponseEntity.badRequest().body("Practice not found!");

        }
    }

    @PostMapping("/credit-card-payment")
    public ResponseEntity<String> ccPayment(@RequestParam String practiceId, @RequestBody Card card){
        boolean check= bankingClientGRPC.practiceExists(practiceId);
        if (check){
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
        boolean check= bankingClientGRPC.practiceExists(practiceId);
        if (check){
            boolean res=bankingClientGRPC.insertBtInfo(practiceId,transfer);
            if(res) return ResponseEntity.ok().body("Payment succeeded"); else return ResponseEntity.badRequest().body("Payment failed!");
        }

        return ResponseEntity.badRequest().body("Practice not found!");
    }

    @PostMapping("/evaluate-practice")
    public ResponseEntity<String> evaluatePractice(@RequestParam String practiceId) throws InvalidProtocolBufferException {
        // Convert the Java UserData object to the Protobuf UserData object
        boolean check = bankingClientGRPC.practiceExists(practiceId);
        if (check) {
            PracticeResponse response = bankingClientGRPC.sendToEvaluation(practiceId);
            return ResponseEntity.ok().body(response.getAllFields().toString());
        } else {
            return ResponseEntity.badRequest().body("Practice not found!");
        }
    }

    @GetMapping("/practice-overview")
        public ResponseEntity<String> practiceOverview(@RequestParam String practiceId) throws InvalidProtocolBufferException {
        boolean check= bankingClientGRPC.practiceExists(practiceId);
        if (check){
            String result= bankingClientGRPC.practiceOverview(practiceId);
            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.badRequest().body("Practice not found!");
        }
    }


    @PostMapping("/user-data")
    public ResponseEntity<Boolean> handleUserData(@RequestParam String practiceId,@RequestBody com.thesis.collectorservice.model.UserDataModels.UserData userData) throws JsonProcessingException, InvalidProtocolBufferException {
        ObjectMapper obj= new ObjectMapper();
        String userDataJson=obj.writeValueAsString(userData);
        UserData.Builder builder=UserData.newBuilder();
        JsonFormat.parser().merge(userDataJson,builder);
        UserData object=builder.build();
        boolean res=bankingClientGRPC.setUserData(practiceId,object);
        if(res) return ResponseEntity.ok().body(true);
        else return ResponseEntity.badRequest().body(false);
    }



}
