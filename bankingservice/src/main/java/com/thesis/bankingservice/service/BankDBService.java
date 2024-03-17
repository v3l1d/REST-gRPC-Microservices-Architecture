package com.thesis.bankingservice.service;


import com.thesis.bankingservice.model.PracticeEntity;
import com.thesis.bankingservice.repository.PracticeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class BankDBService {
    private final Logger logger=LogManager.getLogger(BankDBService.class);
    @Autowired
    private final PracticeRepository repository;
    
    public BankDBService(PracticeRepository practiceRepository){
        this.repository=practiceRepository;
    }
    @Transactional
    public void updatePractice(String practiceId, PracticeEntity entity){
        PracticeEntity toChange=repository.findByPracticeId(practiceId);
        if(toChange!=null){
            toChange=entity;
            repository.save(toChange);
        }else{
            logger.error("Practice not found!");

        }

    }

    @Transactional
    public boolean practiceExists(String practiceId){
        return repository.findByPracticeId(practiceId) != null;

    }

    @Transactional
    public String getFinancingIdByPractice(String practiceId){
        if(repository.findByPracticeId(practiceId)!=null){
            PracticeEntity temp=repository.findByPracticeId(practiceId);
            return temp.getFinancingId();
        }else{
            return null;
        }
    }
    @Transactional
    public void newPractice(PracticeEntity practice){
        if(practice!=null){
            repository.save(practice);
        }
    }

    
}
