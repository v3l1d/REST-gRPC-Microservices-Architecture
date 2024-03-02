package com.thesis.bankingservice.service;


import com.thesis.bankingservice.model.PracticeEntity;
import com.thesis.bankingservice.repository.PracticeRepository;
import com.thesis.generated.Practice;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankDBService {
    @Autowired
    private PracticeRepository repository;
    @Transactional
    public void updatePractice(String practiceId, PracticeEntity entity){
        PracticeEntity toChange=repository.findByPracticeId(practiceId);
        if(toChange!=null){
            toChange=entity;
            repository.save(toChange);
        }

    }

    @Transactional
    public boolean practiceExists(String practiceId){
        return repository.findByPracticeId(practiceId) != null;

    }
    @Transactional
    public void newPractice(PracticeEntity practice){
        if(practice!=null){
            repository.save(practice);
        }
    }
}
