package com.thesis.bankingservice.repository;

import com.thesis.bankingservice.model.PracticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeRepository extends JpaRepository<PracticeEntity,String> {
    PracticeEntity findByPracticeId(String practiceId);
}
