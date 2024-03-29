package com.thesis.financialcalcservice.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.financialcalcservice.Service.CustomerService;
import com.thesis.financialcalcservice.Service.FinancingService;
import com.thesis.financialcalcservice.Service.VehicleService;
import com.thesis.financialcalcservice.model.Customer;
import com.thesis.financialcalcservice.model.Financing;
import com.thesis.financialcalcservice.model.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thesis.financialcalcservice.client.BankingClientREST;
import com.thesis.financialcalcservice.client.MailSmsClientREST;
import java.util.List;

@Profile("rest")
@RestController
public class FinancialControllerREST {

    private final FinancingService financingService;
    private final VehicleService vehicleService;
    private final CustomerService customerService;
    //private final WebClient webClient=WebClient.builder().baseUrl("http://localhost:9093").build();
    private final ObjectMapper obj=new ObjectMapper();
    private final MailSmsClientREST MailSmsClientREST;
    private final BankingClientREST BankingClientREST;
    private final Logger logger=LogManager.getLogger(FinancialControllerREST.class);
  
    public FinancialControllerREST(@Value("${bankingservice.rest.url}") String bankingServiceUrl, @Value("${mailsmsservice.rest.url}") String mailSmsServiceUrl,FinancingService financingService, VehicleService vehicleService,CustomerService customerService) {
        this.financingService = financingService;
        this.vehicleService = vehicleService;
        this.customerService=customerService;
        this.MailSmsClientREST=new MailSmsClientREST(mailSmsServiceUrl);
        this.BankingClientREST=new BankingClientREST(bankingServiceUrl);
    }

    @GetMapping("/get-vehicles")
    public ResponseEntity<List<Vehicle>> listVechiles(@RequestParam String get){
        return ResponseEntity.ok().body(vehicleService.getAllVehicles());
    }

    @GetMapping("/financing-request")
    public ResponseEntity<List<Financing>> listFinancings(@RequestParam String vehicleId){

        return ResponseEntity.ok().body(financingService.getFinancingsByVehicleId(vehicleId));

    }

    @PostMapping("/generate-otp")
    public ResponseEntity<String> generateOtp(@RequestHeader(value="X-Request-ID") String reqId,@RequestBody Customer personalData){
        if(personalData.getEmail()!=null && personalData.getPhone()!=null){
            customerService.savePersonIfNotExists(personalData);
            String mailOtp=MailSmsClientREST.getMailOtp(personalData.getEmail(),reqId);
            String smsOtp=MailSmsClientREST.getSmsOtp(personalData.getPhone(),reqId);
            return ResponseEntity.ok().body("MailOTP: " +mailOtp + " SMSOtp: "+smsOtp);
        }
        else{
            return  ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/verify-otp")
    public ResponseEntity<String> otpVerification(@RequestHeader(value="X-Request-ID") String reqId,@RequestBody String otps,@RequestParam String address){
        try{
        JsonNode passwords=obj.readTree(otps);
        boolean smsVerified=false;
        boolean mailverified=false;
        if(customerService.findCustomerByEmail(address)){
        if(passwords.has("mailOtp")) {
           mailverified=MailSmsClientREST.verifyMail(passwords.get("mailOtp").asText(),reqId);
            if(mailverified){
                customerService.setMailVerified(address);
                return ResponseEntity.ok().body("Mail Verification Completed!");

            }else{
                return ResponseEntity.badRequest().body("PASSWORD INCORRECT!");
            }
        } else
        if(passwords.has("smsOtp")){
            smsVerified=MailSmsClientREST.verifySms(passwords.get("smsOtp").asText(),reqId);
           if(smsVerified){
            customerService.setSMSVerified(address);
            return ResponseEntity.ok().body("SMS Verification Completed!"); 
           }else{
            return ResponseEntity.badRequest().body("INCORRECT PASSWORD!");
           }
        }else{
            return ResponseEntity.badRequest().build();
        }

    }else{
        return ResponseEntity.badRequest().body("User NOT FOUND");
    }
    }catch (Exception e){
        return ResponseEntity.badRequest().build();
    
    }
    }

    @PostMapping("/create-practice")
    public ResponseEntity<String> createPracticeId(@RequestHeader(value="X-Request-ID") String reqId,@RequestBody Customer PersonalData,@RequestParam String financingId){
        Financing temp=financingService.getFinancingById(financingId);
        logger.info("PERSONAL DATA:{}",PersonalData);
        logger.info("FINANCING ID:{}", financingId);
        if(temp!=null){
            String response=BankingClientREST.createPractice(PersonalData, financingId, temp.getLoanAmount(),reqId);
            if(response!=null){
                return ResponseEntity.ok().body("PRACTICE CREATED with ID:" + response);
            }
        }else{
            return ResponseEntity.badRequest().body("FINANCING NOT FOUND! UNABLE TO CREATE PRACTICE!!");
        }


        return ResponseEntity.badRequest().build();
    }



}
