package com.thesis.financialcalcservice.controller;

import com.thesis.financialcalcservice.Service.FinancingService;
import com.thesis.financialcalcservice.Service.VehicleService;
import com.thesis.financialcalcservice.model.Financing;
import com.thesis.financialcalcservice.model.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class FinancialControllerREST {
    private final FinancingService financingService;
    private final VehicleService vehicleService;
    public FinancialControllerREST(FinancingService financingService, VehicleService vehicleService) {
        this.financingService = financingService;
        this.vehicleService = vehicleService;
    }

    @GetMapping("/get-vehicles")
    public ResponseEntity<List<Vehicle>> listVechiles(@RequestParam String action){
        return ResponseEntity.ok().body(vehicleService.getAllVehicles());
    }

    @GetMapping("/financing-request")
    public ResponseEntity<List<Financing>> listFinancings(@RequestParam String vehicleId){

        return ResponseEntity.ok().body(financingService.getFinancingsByVehicleId(vehicleId));

    }





}
