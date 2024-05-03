package com.thesis.financialcalcservice.repository;

import com.thesis.financialcalcservice.model.Financing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancingRepository extends JpaRepository<Financing,String> {
    List<Financing> findByVehicleId(String vehicleId);
    Financing findByFinancingId(String financingId);


}
