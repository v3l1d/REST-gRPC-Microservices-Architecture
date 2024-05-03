package com.thesis.financialcalcservice.Service;

import com.thesis.financialcalcservice.model.Vehicle;
import com.thesis.financialcalcservice.repository.VehicleRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    private final VehicleRepository vehicleRepository;


    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles(){
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicleById(String id){ return vehicleRepository.findByVehicleId(id);}
}
