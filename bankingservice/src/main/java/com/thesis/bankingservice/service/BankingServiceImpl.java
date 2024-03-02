package com.thesis.bankingservice.service;

import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.thesis.bankingservice.model.PracticeEntity;
import com.thesis.bankingservice.repository.PracticeRepository;
import com.thesis.generated.BankingGrpc;
import com.thesis.generated.Practice;
import com.thesis.generated.PracticeResponse;
import com.thesis.generated.Request;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import jakarta.annotation.PostConstruct;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class BankingServiceImpl  extends BankingGrpc.BankingImplBase {

    @Autowired
    private BankDBService dbService;
    private String tempId;


   @Autowired
   public void setDbService(BankDBService dbService){
        this.dbService=dbService;
   }



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
        String status="CREATED";
        tempId=practiceIdGen();
        String practiceId=tempId;
        
        PracticeEntity entity= new PracticeEntity();
        entity.setStatus(status);
        entity.setPracticeId(practiceId);
      // practiceRepository.save(entity);

            dbService.newPractice(entity);
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
    
      if(request.getReq().getAction() == Request.Action.FILL && dbService.practiceExists(request.getPracticeId())){
            String status="COMPLETED";
            Practice practice=Practice.newBuilder()
                .setStatus(status)
                .setPracticeId(request.getPracticeId())
                .setName(request.getName())
                .setSurname(request.getSurname())
                .setEmail(request.getEmail())
                .setPhone(request.getPhone())
                .build();

            PracticeEntity temp=new PracticeEntity();
            temp.setStatus(status);
            temp.setPracticeId(request.getPracticeId());
            temp.setName(request.getName());
            temp.setSurname(request.getSurname());
            temp.setEmail(request.getEmail());
            temp.setPhone(request.getPhone());
            dbService.updatePractice(request.getPracticeId(), temp);

            PracticeResponse response=PracticeResponse.newBuilder()
                .setPracticeId(tempId)
                .setStatus(status)
                .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
      }else {
          responseObserver.onError(Status.INVALID_ARGUMENT
                  .withDescription("ERROR! Practice ID not found")
                  .asRuntimeException());

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
