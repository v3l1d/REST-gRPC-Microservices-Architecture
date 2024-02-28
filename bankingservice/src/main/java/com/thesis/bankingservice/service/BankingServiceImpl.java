package com.thesis.bankingservice.service;

import java.util.Random;

import com.thesis.generated.BankingGrpc;
import com.thesis.generated.Practice;
import com.thesis.generated.PracticeResponse;
import com.thesis.generated.Request;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class BankingServiceImpl  extends BankingGrpc.BankingImplBase {
    private String practiceId;
    private String status;
    private Practice practice;
   

    @Override
    public void createPractice(Practice request ,StreamObserver<PracticeResponse> responseObserver){
          
        if (request == null || request.getReq() == null) {
        // Handle null request or req field
        responseObserver.onError(Status.INVALID_ARGUMENT
                .withDescription("Invalid request: req field is missing or null")
                .asRuntimeException());
        return;
    }
        if(request.getReq().getAction()==Request.Action.CREATE){
        this.status="CREATED";
        this.practiceId=practiceIdGen();
       
        PracticeResponse resp= PracticeResponse.newBuilder()
            .setStatus(status)
            .setPracticeId(practiceId)
            .build();
        
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
      } else {
        // Handle other actions
        responseObserver.onError(Status.INVALID_ARGUMENT
                .withDescription("Invalid action: only CREATE action is supported")
                .asRuntimeException());
    }
    }   

    @Override
    public void fillPractice(Practice request, StreamObserver<PracticeResponse> responseObserver){
      if(request==null || request.getReq()==null){
            responseObserver.onError(Status.INVALID_ARGUMENT
                .withDescription("Invalid request: missing fields")
                .asRuntimeException());
            return;

      } 
    
      if(request.getReq().getAction() == Request.Action.FILL && request.getPracticeId().equals(practiceId)){
            this.status="COMPLETED";
            this.practice=Practice.newBuilder()
                .setStatus(status)
                .setPracticeId(practiceId)
                .setName(request.getName())
                .setSurname(request.getSurname())
                .setEmail(request.getEmail())
                .setPhone(request.getPhone())
                .build();
            PracticeResponse response=PracticeResponse.newBuilder()
                .setPracticeId(practiceId)
                .setStatus(status)
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

        return practId.toString();
    }
        
}   
