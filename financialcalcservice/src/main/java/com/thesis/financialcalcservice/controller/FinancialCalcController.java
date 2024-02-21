package com.thesis.financialcalcservice.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.financialcalcservice.Service.MailSmsClient;

@RestController
public class FinancialCalcController {
    @GetMapping("/calculation")
    public String getMethodName(@RequestBody String param) {
        return "Param: " + param;
    }

    @PostMapping("/verification")
    public String verification(@RequestBody String entity) {
        ObjectMapper obj= new ObjectMapper();
         
         
         try{
            JsonNode jsonNode=obj.readTree(entity);
            
         if(jsonNode.has("prefix") && jsonNode.has("number")){
                MailSmsClient temp= new MailSmsClient("localhost", 50053);
                temp.sendSmsOtp(jsonNode.get("prefix").toString(), jsonNode.get("number").toString());
            return "requestforwarded";
         }else{
            return "no";
         }
        } catch (Exception e){
            e.getStackTrace();
        }
        return null;

         
    }
    
    
}
