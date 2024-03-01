package com.thesis.financialcalcservice.repository;

import com.thesis.financialcalcservice.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.util.annotation.NonNull;

import java.util.List;
//P@ssword!2024
public interface VehicleRepository extends JpaRepository<Vehicle,String> {
    List<Vehicle> findByModel(int model);
    @NonNull
    List<Vehicle> findAll();

}
