package com.thesis.financialcalcservice.Service;

import com.thesis.financialcalcservice.model.Financing;
import com.thesis.financialcalcservice.repository.FinancingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancingService {
    @Autowired
    private final FinancingRepository financingRepository;


    public FinancingService(FinancingRepository financingRepository){
        this.financingRepository=financingRepository;
    }

    public List<Financing> getFinancingsByVehicleId(String id){
        return financingRepository.findByVehicleId(id);
    }

    public Financing getFinancingById(String id){
        return financingRepository.findByFinancingId(id);
    }


}
