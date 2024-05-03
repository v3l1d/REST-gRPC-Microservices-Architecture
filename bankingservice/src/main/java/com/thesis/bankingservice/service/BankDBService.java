package com.thesis.bankingservice.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeCodec;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thesis.bankingservice.model.AdditionalInfo;
import com.thesis.bankingservice.model.PracticeEntity;
import com.thesis.bankingservice.repository.PracticeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class BankDBService {
    private final Logger logger=LogManager.getLogger(BankDBService.class);

    private final PracticeRepository repository;
    private ObjectMapper objectMapper;

    @Autowired
    public BankDBService(PracticeRepository practiceRepository, ObjectMapper objectMapper){
        this.repository=practiceRepository;
        this.objectMapper=objectMapper;

    }

    @Transactional
    public PracticeEntity getFullPractice(String practiceId){
        if(practiceExists(practiceId)){
            return repository.findByPracticeId(practiceId);
        }else{
            return null;
        }
    }
    @Transactional
    public void updatePractice(String practiceId, String additionalInfo){
        PracticeEntity toChange=repository.findByPracticeId(practiceId);
        if(toChange!=null) {
            toChange.setAdditionalInfo(additionalInfo);
            repository.save(toChange);
        }
    }

    @Transactional
    public void setPracticeToCompleted(String practiceId){
        PracticeEntity toChange=repository.findByPracticeId(practiceId);
        toChange.setStatus("COMPLETED");
        repository.save(toChange);
    }

    @Transactional
    public boolean practiceExists(String practiceId){
        return repository.findByPracticeId(practiceId) != null;

    }

    @Transactional
    public void setPaymentMethod(String practiceId,String paymentMethod,String paymentInfo){
        PracticeEntity temp=repository.findByPracticeId(practiceId);
        if(temp!=null && temp.getPaymentMethod()==null){
            temp.setPaymentMethod(paymentMethod);
            temp.setPaymentInfo(paymentInfo);
        }
    }

    @Transactional
    public String getFinancingIdByPractice(String practiceId){
        if(repository.findByPracticeId(practiceId)!=null){
            PracticeEntity temp=repository.findByPracticeId(practiceId);
            return temp.getFinancingInfo();
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
