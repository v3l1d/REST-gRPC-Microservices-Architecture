package com.thesis.financialcalcservice.controller;

import brave.grpc.GrpcTracing;
import com.thesis.financialcalcservice.Service.VehicleService;
import com.thesis.financialcalcservice.client.BankingClientGRPC;
import com.thesis.financialcalcservice.Service.CustomerService;
import com.thesis.financialcalcservice.Service.FinancingService;
import com.thesis.financialcalcservice.repository.VehicleRepository;

import io.micrometer.observation.annotation.Observed;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.financialcalcservice.client.MailSmsClientGRPC;
import com.thesis.financialcalcservice.model.Financing;
import com.thesis.financialcalcservice.model.Vehicle;
import com.thesis.financialcalcservice.model.Customer;

import org.springframework.web.bind.annotation.RequestParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;

@Profile("grpc")
@RestController

public class FinancialControllerGrpc {
   private final MailSmsClientGRPC mailSmsClientGRPC;
   private final BankingClientGRPC bankingClientGRPC;
   private final String grpcBankServer;
   private final String mailSmsServer;
   private String MailOtp;
   private String SmsOtp;
   private final ObjectMapper obj= new ObjectMapper();
   private static final Logger logger=LogManager.getLogger(FinancialControllerGrpc.class);
   private final VehicleService vehicleService;
   private final CustomerService customerService;
   private final FinancingService financingService;

    public FinancialControllerGrpc(@Value("${bankingservice.grpc.url}")String bankingHost, @Value("${mailsmsservice.grpc.url}") String mailsmsHost, FinancingService financingService, CustomerService customerService, GrpcTracing grpcTracing, VehicleService vehicleService) {
        this.vehicleService = vehicleService;
        this.customerService = customerService;
        this.grpcBankServer=bankingHost;
        this.mailSmsServer=mailsmsHost;
        this.bankingClientGRPC=new BankingClientGRPC(grpcBankServer,grpcTracing);
        this.mailSmsClientGRPC=new MailSmsClientGRPC(mailSmsServer,grpcTracing);
        this.financingService = financingService;
    }

    @GetMapping("/get-vehicles")
    @Observed
    public List<Vehicle> getVehiclesList(@RequestHeader(value="X-Request-ID") String reqId) {
        logger.info("Request id: {}", reqId);
        return vehicleService.getAllVehicles();




    }

    @GetMapping("/financing-request")
    @Observed
    public ResponseEntity<List<Financing>> listFinancings(@RequestHeader(value="X-Request-ID") String reqId,@RequestParam String vehicleId) {
        logger.info("Request ID:{} INPUT:{} OUTPUT:{}",reqId,vehicleId,financingService.getFinancingsByVehicleId(vehicleId));
        return ResponseEntity.ok().body(financingService.getFinancingsByVehicleId(vehicleId));

    }

    @PostMapping("/verify-otp")
    @Observed
    public ResponseEntity<String> otpVerification(@RequestBody String otps , @RequestParam String address) {
        try {

                JsonNode passwords = obj.readTree(otps);
                boolean smsVerified = false;
                boolean mailVerified = false;
                if(customerService.findCustomerByEmail(address)) {
                if (passwords.has("mailOtp")) {
                    mailVerified = mailSmsClientGRPC.verifyMail(passwords.get("mailOtp").asText());
                    logger.info(mailVerified);
                    if (mailVerified) {
                        customerService.setMailVerified(address);
                        return ResponseEntity.ok().body("Mail OTP verification completed!");
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mail OTP verification failed!");
                    }
                }
                if (passwords.has("smsOtp") && customerService.findCustomerByEmail(address)) {
                    smsVerified = mailSmsClientGRPC.verifySms(passwords.get("smsOtp").asText());
                    logger.info(smsVerified);
                    if (smsVerified) {
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


    @PostMapping("/generate-otp")
    public ResponseEntity<String> generateOtp(@RequestBody Customer personalData){

        if(personalData.getEmail()!=null && personalData.getPhone()!=null){
            customerService.savePersonIfNotExists(personalData);
            this.SmsOtp= mailSmsClientGRPC.createSmsOtp(personalData.getPhone());
            this.MailOtp= mailSmsClientGRPC.createMailOtp(personalData.getEmail());

            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("{\"MailOTP\": \"" + MailOtp + "\", \"SmsOtp\": \"" + SmsOtp + "\"}");
        }
        else{
            return  ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/create-practice")
    public ResponseEntity<String> createPracticeId(@RequestBody Customer personalData,@RequestParam String financingId) throws JsonProcessingException {
        try{
            if(customerService.findCustomerByEmail(personalData.getEmail()) && financingService.getFinancingById(financingId)!=null){
                logger.info("condition verified");
                Financing financingTemp=financingService.getFinancingById(financingId);
                Vehicle vehicleTemp=vehicleService.getVehicleById(financingTemp.getVehicleId());
                String resp= bankingClientGRPC.createPractice(personalData,financingTemp,vehicleTemp);
                logger.info(resp);
                if(resp!=null) {
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
