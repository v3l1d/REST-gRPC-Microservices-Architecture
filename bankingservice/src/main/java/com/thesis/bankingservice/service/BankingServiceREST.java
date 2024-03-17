package com.thesis.bankingservice.service;

import java.util.Random;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.thesis.bankingservice.model.PracticeEntity;


@Profile("rest")
@Service
public class BankingServiceREST {
    private BankDBService dbService;
    
    public BankingServiceREST(BankDBService dbService){
        this.dbService=dbService;
        
    }

  


    public String createPractice( String name, String surname, String phone,String customerEmail,String financingId,double amount){
        
            PracticeEntity newPractice= new PracticeEntity();
            newPractice.setPracticeId(practiceIdGen());
            newPractice.setName(name);
            newPractice.setSurname(surname);
            newPractice.setEmail(customerEmail);
            newPractice.setPhone(phone);
            newPractice.setFinancingId(financingId);
            newPractice.setAmount(amount);
            newPractice.setStatus("CREATED");
            dbService.newPractice(newPractice);

            if(dbService.practiceExists(newPractice.getPracticeId())){
                return newPractice.getPracticeId();
            } else{
                return null;
            }
    }
    
    

    public String practiceIdGen(){
        StringBuilder practId= new StringBuilder();
        String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand=new Random();
        for(int i=0;i<2;i++){
            practId.append(LETTERS.charAt(rand.nextInt(LETTERS.length())));
        }
        for(int i=2;i<5;i++){
            practId.append(rand.nextInt(19));
        }

        return practId.toString();
    }

}
