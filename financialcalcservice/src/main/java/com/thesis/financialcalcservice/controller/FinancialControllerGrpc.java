package com.thesis.financialcalcservice.controller;

import com.thesis.financialcalcservice.Service.BankingClient;
import com.thesis.financialcalcservice.repository.FinancingRepository;
import com.thesis.financialcalcservice.Service.PersonService;
import com.thesis.financialcalcservice.repository.VehicleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
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

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class FinancialControllerGrpc {
   private final MailSmsClient mailSmsClient = new MailSmsClient("localhost", 50053);
   private final BankingClient bankingClient= new BankingClient("localhost",50054);

   private String MailOtp;
   private String SmsOtp;
   private final ObjectMapper obj= new ObjectMapper();
   private static final Logger logger=LogManager.getLogger(FinancialControllerGrpc.class);
   private final VehicleRepository vehicleRepository;
   private final FinancingRepository financingRepository;
   private final PersonService personService;


    public FinancialControllerGrpc(VehicleRepository vehicleRepository, FinancingRepository financingRepository, PersonService personService) {
        this.vehicleRepository = vehicleRepository;
        this.financingRepository = financingRepository;
        this.personService=personService;
    }

    @GetMapping("/get-vehicles")
    public List<Vehicle> getVehiclesList(@RequestBody String param) {
        return vehicleRepository.findAll();




    }

    @GetMapping("/financing-request")
    public List<Financing> getMethodName(@RequestParam String id) {

        logger.info(id);
        logger.info("VEHICLED REQUESTED: {} \n AVAILABLE FINANCINGS FOR THIS ID : {}",id,financingRepository.findByVehicleId(id));
        return financingRepository.findByVehicleId(id);

    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> otpVerification(@RequestBody String otps , String email) {
        try {
            JsonNode passwords = obj.readTree(otps);
            boolean SMSVerified = false;
            boolean MailVerified = false;

            if (passwords.has("email")) {
                MailVerified = mailSmsClient.verifyMail(passwords.get("email").asText());
                logger.info(MailVerified);
                if (MailVerified) {
                    return ResponseEntity.ok().body("Mail OTP verification completed!");
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mail OTP verification failed!");
                }
            } else if (passwords.has("sms")) {
                SMSVerified = mailSmsClient.verifySms(passwords.get("sms").asText());
                logger.info(SMSVerified);
                if (SMSVerified) {
                    return ResponseEntity.ok().body("SMS OTP verification completed!");
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("SMS OTP verification failed!");
                }
            } else {
                return ResponseEntity.badRequest().body("Either email or SMS OTP is required!");
            }

        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing OTPs!");
        }
    }


    @PostMapping("/personal-data")
      public ResponseEntity<ObjectNode> dataProcessing(@RequestBody Person personalData) {

            try {
                if (personalData.getEmail()!=null && personalData.getPhone()!=null) {
                    personService.savePersonIfNotExists(personalData);
                    String jsonBody = obj.writeValueAsString(personalData);
                    logger.info(jsonBody);
                    WebClient webClient=WebClient.create();
                    ObjectNode response= webClient.post()
                            .uri("http://localhost:9092/generate-otp")
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromValue(jsonBody))
                            .retrieve()
                            .bodyToMono(ObjectNode.class)
                            .block();
                    if(response!=null){
                        return  ResponseEntity.ok(response);
                    }else {
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                }
            } catch (Exception e) {

                logger.error("Error processing Personal Data",e);
            }

            return ResponseEntity.badRequest().build();
        }



    @PostMapping("/generate-otp")
    public ObjectNode verification(@RequestBody Person personalData) {
        ObjectNode response=obj.createObjectNode();

         
         try{
             if (personalData.getPhone()!=null && personalData.getEmail()!=null) {
            logger.info("Both phone and email are present {} {}:" ,personalData.getEmail() , personalData.getPhone());
            this.SmsOtp= mailSmsClient.createSmsOtp(personalData.getPhone().substring(0,1), personalData.getPhone().substring(2,11));
            this.MailOtp= mailSmsClient.createMailOtp(personalData.getEmail());
            logger.info(this.SmsOtp);
            logger.info(this.MailOtp);
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
    public String createPracticeId(@RequestParam String action) {
        if (action.equals("CREATE")) {
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

        bankingClient.fillPractice(personalData,"asd213"
        );
    }

    
    
}
