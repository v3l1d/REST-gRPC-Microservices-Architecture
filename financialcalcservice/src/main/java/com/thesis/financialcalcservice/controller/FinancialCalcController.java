package com.thesis.financialcalcservice.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thesis.financialcalcservice.Service.MailSmsClient;
import com.thesis.financialcalcservice.model.Financing;
import com.thesis.financialcalcservice.model.Vehicle;
import com.thesis.generated.Mail;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;


@RestController
public class FinancialCalcController {
   private final MailSmsClient temp= new MailSmsClient("localhost", 50053);
   private String MailOtp;
   private String SmsOtp;
   private ObjectMapper obj= new ObjectMapper();

    @GetMapping("/getvehicles")
    public List<Vehicle> getVehiclesList(@RequestBody String param) {
        List<Vehicle> vehicles= new ArrayList<>();
        vehicles.add(new Vehicle("T123","Toyota", "Camry", 2022));
        vehicles.add(new Vehicle("H123","Honda", "Accord", 2023));
        vehicles.add(new Vehicle("F123","Ford", "Fusion", 2021));
        return vehicles;


    }

    @GetMapping("/financingrequest")
    public List<Financing> getMethodName(@RequestParam String vehicleId) {
        List<Financing> availables=new ArrayList<>();
            availables.add(new Financing("F12","T123","John Doe",14000.00,32));
        
        for(Financing x : availables){
            if(vehicleId.equals(x.getVehicleId())){
                System.out.println("Financing proposal available");
            }
        }
        return availables;

    }
    
    @PostMapping("/personaldata")
      public Mono<ObjectNode> dataProcessing(@RequestBody Map<String, String> data) {
            try {
                if (data.containsKey("number") && data.containsKey("email")) {
                    String jsonBody = obj.writeValueAsString(Map.of(
                            "number", data.get("number"),
                            "email", data.get("email")
                    ));
                    WebClient webClient=WebClient.create();

                    return webClient.post()
                            .uri("http://localhost:9092/verification")
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromValue(jsonBody))
                            .retrieve()
                            .bodyToMono(ObjectNode.class)
                            .doOnNext(response -> System.out.println("Verification Response: " + response));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    
            return Mono.empty();
        }
    


    @PostMapping("/verification")
    public ObjectNode verification(@RequestBody String entity) {
     
        ObjectNode response=obj.createObjectNode();
         
         try{
            JsonNode jsonNode=obj.readTree(entity);
            
         if(jsonNode.has("number") && jsonNode.has("email")){
            this.SmsOtp= temp.sendSmsOtp(jsonNode.get("number").toString().substring(0, 1), jsonNode.get("number").toString().substring(2,11));
            this.MailOtp= temp.sendMailOtp(jsonNode.get("email").toString());
            response.put("SMS OTP", SmsOtp);
            response.put("Mail OTP", MailOtp);

         }else{
            response.put("SMS OTP", "Null");
            response.put("Mail OTP", "Null");
            
         }
        } catch (Exception e){
            e.getStackTrace();
        }
        return response;

         
    }
    public void createPractice(){

    }
    
   @PostMapping("/verify")
public Boolean verifyMailOtp(@RequestBody String otpJsonString) {
    JsonNode otpJsonNode;
    Boolean res = false;
    try {
        otpJsonNode = obj.readTree(otpJsonString);
        String otpValue = otpJsonNode.get("otp").asText();
        res=temp.verifyMail(otpValue);
    } catch (JsonProcessingException e) {
        e.printStackTrace();
    }
    return res;
}

    @GetMapping("/getpractice")
    public String getPracticeId(@RequestParam String param) {
        return new String();
    }
    
    
}
