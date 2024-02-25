package com.thesis.bankingservice.service;

import java.util.Random;

import com.thesis.bankingservice.generated.BankingGrpc;
import com.thesis.bankingservice.generated.Practice;
import com.thesis.bankingservice.generated.PracticeResponse;
import com.thesis.bankingservice.generated.Request;

import io.grpc.stub.StreamObserver;

public class BankingServiceImpl  extends BankingGrpc.BankingImplBase {
    private String practiceId;

    @Override
    public void createPractice(Request req,StreamObserver<Practice> responseObserver){
            if(req.getAction().equals(Request.Action.FILL)){
                String practId=practiceIdGen();
                this.practiceId=practId;
                Practice response=Practice.newBuilder()
                    .setPracticeId(practId)
                    .setStatus("TEMPORARY")
                    .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
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

        return practiceId;
    }
        
}   
