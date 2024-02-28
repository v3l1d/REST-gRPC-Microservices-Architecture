package com.thesis.financialcalcservice.controller;

import com.thesis.financialcalcservice.Service.BankingClient;
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
import com.thesis.financialcalcservice.model.Person;

import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class FinancialCalcController {
   private final MailSmsClient mailSmsClient = new MailSmsClient("localhost", 50053);
   private final BankingClient bankingClient= new BankingClient("localhost",50054);
   private String MailOtp;
   private String SmsOtp;
   private final ObjectMapper obj= new ObjectMapper();
   private static final Logger logger=LogManager.getLogger(FinancialCalcController.class);

    @GetMapping("/get-vehicles")
    public List<Vehicle> getVehiclesList(@RequestBody String param) {
        List<Vehicle> vehicles= new ArrayList<>();
        vehicles.add(new Vehicle("T123","Toyota", "Camry", 2022));
        vehicles.add(new Vehicle("H123","Honda", "Accord", 2023));
        vehicles.add(new Vehicle("F123","Ford", "Fusion", 2021));
        return vehicles;


    }

    @GetMapping("/financing-request")
    public List<Financing> getMethodName(@RequestParam String vehicleId) {
        List<Financing> available=new ArrayList<>();
            available.add(new Financing("F12","T123","John Doe",14000.00,32));
        
        for(Financing x : available){
            if(vehicleId.equals(x.getVehicleId())){
                System.out.println("Financing proposal available");
            }
        }
        return available;

    }

    @PostMapping("/verify-otp")
    public void otpVerification(@RequestBody String otps) throws JsonProcessingException {
        boolean smsResponse=false;
        boolean mailResponse=false;
        JsonNode passwords=obj.readTree(otps);
        if(passwords.has("email")){
            mailResponse= mailSmsClient.verifyMail(passwords.get("email").asText());
            System.out.println(mailResponse);
        }
        if(passwords.has("sms")){
            smsResponse= mailSmsClient.verifySms(passwords.get("sms").asText());
            System.out.println(smsResponse);
        }

    }
    
    @PostMapping("/personal-data")
      public Mono<ObjectNode> dataProcessing(@RequestBody Person personalData) {

            try {
                if (personalData.getEmail()!=null && personalData.getPhone()!=null) {
                    String jsonBody = obj.writeValueAsString(personalData);
                    WebClient webClient=WebClient.create();
                    logger.info("JSON Body:{}",jsonBody);
                    return webClient.post()
                            .uri("http://localhost:9092/generate-otp")
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



    @PostMapping("/generate-otp")
    public ObjectNode verification(@RequestBody String entity) {
     
        ObjectNode response=obj.createObjectNode();
         
         try{
            JsonNode jsonNode=obj.readTree(entity);
            logger.info("RECEIVED DATA: {} \n phone: {}",jsonNode,jsonNode.get("phone"));
            
         if(jsonNode.has("phone") && jsonNode.has("email")){
            this.SmsOtp= mailSmsClient.createSmsOtp(jsonNode.get("phone").toString().substring(0, 1), jsonNode.get("number").toString().substring(2,11));
            this.MailOtp= mailSmsClient.createMailOtp(jsonNode.get("email").asText());
            response.put("SMS OTP", SmsOtp);
            response.put("Mail OTP", MailOtp);
            logger.info(response);
         }else{
            response.put("SMS OTP", "Null");
            response.put("Mail OTP", "Null");
            
         }
        } catch (Exception e){
            e.getStackTrace();
        }
        return response;

         
    }





    @GetMapping("/create-practice")
    public String getPracticeId(@RequestParam String param) {

        if (param.equals("CREATE")) {
            if (bankingClient.createPractice()) {
                return "Practice created";
            } else {

                return "Practice not Created!";

            }
        }
        return null;
    }


    @PostMapping("/fill-practice")
    public void fillPractice(@RequestBody Person personalData){
        bankingClient.fillPractice(personalData,"asd213");
    }

    
    
}
