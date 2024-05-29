package com.thesis.financialcalcservice.repository;

import com.thesis.financialcalcservice.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reactor.util.annotation.NonNull;

import java.util.List;
import java.util.Optional;

//P@ssword!2024
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,String> {


    List<Vehicle> findByYear(int model);

    Vehicle findByVehicleId(String vehicleId);
    @Override
    @NonNull
    List<Vehicle> findAll();

}
