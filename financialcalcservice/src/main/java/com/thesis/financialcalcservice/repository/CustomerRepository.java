package com.thesis.financialcalcservice.repository;

import com.thesis.financialcalcservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {

    Customer findByEmail(String email);


}
