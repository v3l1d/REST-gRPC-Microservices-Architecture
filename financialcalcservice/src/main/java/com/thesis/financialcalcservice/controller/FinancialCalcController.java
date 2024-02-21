package com.thesis.financialcalcservice.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.financialcalcservice.Service.MailSmsClient;
import com.thesis.financialcalcservice.model.Vehicle;

@RestController
public class FinancialCalcController {
    @GetMapping("/getvehicles")
    public List<Vehicle> getVehiclesList(@RequestBody String param) {
        List<Vehicle> vehicles= new ArrayList<>();
        vehicles.add(new Vehicle("Toyota", "Camry", 2022));
        vehicles.add(new Vehicle("Honda", "Accord", 2023));
        vehicles.add(new Vehicle("Ford", "Fusion", 2021));
        return vehicles;


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
