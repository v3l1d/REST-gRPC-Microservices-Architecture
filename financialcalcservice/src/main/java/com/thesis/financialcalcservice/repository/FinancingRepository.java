package com.thesis.financialcalcservice.repository;

import com.thesis.financialcalcservice.model.Financing;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FinancingRepository extends JpaRepository<Financing,String> {
    List<Financing> findByVehicleId(String vehicleId);
    Financing findByFinancingId(String financingId);

}
