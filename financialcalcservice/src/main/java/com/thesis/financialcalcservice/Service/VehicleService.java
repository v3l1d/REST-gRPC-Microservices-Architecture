package com.thesis.financialcalcservice.Service;

import com.thesis.financialcalcservice.model.Vehicle;
import com.thesis.financialcalcservice.repository.VehicleRepository;

import java.util.List;

public class VehicleService {
    private final VehicleRepository vehicleRepository;


    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }
}
