package com.thesis.financialcalcservice.controller;

import com.thesis.financialcalcservice.client.BankingClientGRPC;
import com.thesis.financialcalcservice.repository.FinancingRepository;
import com.thesis.financialcalcservice.Service.CustomerService;
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
import com.thesis.financialcalcservice.client.MailSmsClientGRPC;
import com.thesis.financialcalcservice.model.Financing;
import com.thesis.financialcalcservice.model.Vehicle;
import com.thesis.financialcalcservice.model.Customer;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class FinancialControllerGrpc {
   private final MailSmsClientGRPC mailSmsClientGRPC = new MailSmsClientGRPC("localhost", 50053);
   private final BankingClientGRPC bankingClientGRPC = new BankingClientGRPC("localhost",50054);

   private String MailOtp;
   private String SmsOtp;
   private final ObjectMapper obj= new ObjectMapper();
   private static final Logger logger=LogManager.getLogger(FinancialControllerGrpc.class);
   private final VehicleRepository vehicleRepository;
   private final FinancingRepository financingRepository;
   private final CustomerService customerService;


    public FinancialControllerGrpc(VehicleRepository vehicleRepository, FinancingRepository financingRepository, CustomerService customerService) {
        this.vehicleRepository = vehicleRepository;
        this.financingRepository = financingRepository;
        this.customerService = customerService;
    }

    @GetMapping("/get-vehicles")
    public List<Vehicle> getVehiclesList(@RequestBody String param) {
        return vehicleRepository.findAll();




    }

    @GetMapping("/financing-request")
    public List<Financing> listFinancings(@RequestParam String id) {

        logger.info(id);
        logger.info("VEHICLED REQUESTED: {} \n AVAILABLE FINANCINGS FOR THIS ID : {}",id,financingRepository.findByVehicleId(id));
        return financingRepository.findByVehicleId(id);

    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> otpVerification(@RequestBody String otps , @RequestParam String address) {
        try {

                JsonNode passwords = obj.readTree(otps);
                boolean SMSVerified = false;
                boolean MailVerified = false;
                if(customerService.findCustomerByEmail(address)) {
                if (passwords.has("emailOtp")) {
                    MailVerified = mailSmsClientGRPC.verifyMail(passwords.get("emailOtp").asText());
                    logger.info(MailVerified);
                    if (MailVerified) {
                        customerService.setMailVerified(address);
                        return ResponseEntity.ok().body("Mail OTP verification completed!");
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mail OTP verification failed!");
                    }
                }
                if (passwords.has("smsOtp") && customerService.findCustomerByEmail(address)) {
                    SMSVerified = mailSmsClientGRPC.verifySms(passwords.get("smsOtp").asText());
                    logger.info(SMSVerified);
                    if (SMSVerified) {
                        customerService.setSMSVerified(address);
                        return ResponseEntity.ok().body("SMS OTP verification completed!");
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("SMS OTP verification failed!");
                    }
                } else {
                    return ResponseEntity.badRequest().body("Either email or SMS OTP is required!");
                }
            }else{
                    return ResponseEntity.badRequest().body("User not found!");
                }

        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing OTPs!");
        }

    }


    @PostMapping("/personal-data")
      public ResponseEntity<ObjectNode> dataProcessing(@RequestBody Customer personalData) {

            try {

                if (personalData.getEmail()!=null && personalData.getPhone()!=null) {
                    customerService.savePersonIfNotExists(personalData);
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
    public ObjectNode verification(@RequestBody Customer personalData) {
        ObjectNode response=obj.createObjectNode();

         
         try{
             if (personalData.getPhone()!=null && personalData.getEmail()!=null){
                 customerService.savePersonIfNotExists(personalData);
            logger.info("Both phone and email are present {} {}:" ,personalData.getEmail() , personalData.getPhone());
            this.SmsOtp= mailSmsClientGRPC.createSmsOtp(personalData.getPhone().substring(0,1), personalData.getPhone().substring(2,11));
            this.MailOtp= mailSmsClientGRPC.createMailOtp(personalData.getEmail());
            response.put("SMS OTP", SmsOtp);
            response.put("Mail OTP", MailOtp);
            response.put("Customer ID:", personalData.getId());
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





    @PostMapping("/create-practice")
    public ResponseEntity<String> createPracticeId(@RequestBody String reqBody) throws JsonProcessingException {
        JsonNode body=obj.readTree(reqBody);
        String customerEmail=body.get("customerEmail").asText();
        String financingID=body.get("financingId").asText();
        logger.info("Body: {} , customerEmail: {} ,financingId: {}",body,customerEmail,financingID);
        try{
            if(customerService.findCustomerByEmail(customerEmail) && financingRepository.findByFinancingId(financingID)!=null){
                logger.info("condition verified");
                Financing financingTemp=financingRepository.findByFinancingId(financingID);
                double amount=financingTemp.getLoanAmount();
                logger.info("AMOUNT VALUE IN CONTROLLER {}:",amount);
                String resp= bankingClientGRPC.createPractice(customerEmail,financingID,amount);
                logger.info(resp);
                if(resp!=null) {
                    Customer temp=customerService.getCustomerByEmail(customerEmail);
                    bankingClientGRPC.fillPractice(temp,resp,amount);
                    return ResponseEntity.ok("Practice successfully created with id: "+resp);
                }
            }else{
                return ResponseEntity.internalServerError().body("Customer not found!");
            }
        } catch (Exception e){
            logger.error("ERROR CREATING PRACTICE",e);
        }

        return ResponseEntity.badRequest().build();

    }




    
    
}
