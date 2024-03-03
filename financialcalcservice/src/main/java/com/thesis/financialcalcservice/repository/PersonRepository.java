package com.thesis.financialcalcservice.repository;

import com.thesis.financialcalcservice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {

    Person findByEmail(String email);
}
