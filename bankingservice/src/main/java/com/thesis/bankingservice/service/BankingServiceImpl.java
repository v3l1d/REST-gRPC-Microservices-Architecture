package com.thesis.bankingservice.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

import com.thesis.bankingservice.model.AdditionalInfo;
import com.thesis.bankingservice.model.Financing;
import com.thesis.bankingservice.model.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.thesis.bankingservice.model.PracticeEntity;
import com.thesis.generated.BankingGrpc;
import com.thesis.generated.Practice;
import com.thesis.generated.PracticeResponse;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.context.annotation.Profile;

@Profile("grpc")
@GrpcService(interceptors = {HeaderInterceptor.class})
public class BankingServiceImpl  extends BankingGrpc.BankingImplBase {
    private final Logger logger=LogManager.getLogger(BankingServiceImpl.class);
    @Autowired
    private BankDBService dbService;
    private String tempId;


   @Autowired
   public void setDbService(BankDBService dbService){
        this.dbService=dbService;
   }



    @Override
    public void createPractice(Practice request ,StreamObserver<PracticeResponse> responseObserver){

        if(request!=null){
            if(!dbService.practiceExists(request.getPracticeId())){
                String status="CREATED";
                tempId=practiceIdGen();
                String practiceId=tempId;
                PracticeEntity entity= new PracticeEntity();
                entity.setStatus(status);
                entity.setPracticeId(practiceId);
                entity.setEmail(request.getEmail());
                entity.setPhone(request.getPhone());
                entity.setName(request.getName());
                entity.setSurname(request.getSurname());
                Financing finTemp= new Financing(request.getFinancingInfo().getFinancingId(),
                        request.getFinancingInfo().getVehicleId(),
                        request.getFinancingInfo().getLoanAmount(),
                        request.getFinancingInfo().getLoanTerm());
                entity.setFinancingInfo(finTemp.toString());
                Vehicle vehicleTemp=new Vehicle(request.getVehicleInfo().getVehicleId(),
                        request.getVehicleInfo().getBrand(),
                        request.getVehicleInfo().getName(),
                        request.getVehicleInfo().getYear());
                entity.setVehicleInfo(vehicleTemp.toString());

                dbService.newPractice(entity);
                PracticeResponse resp= PracticeResponse.newBuilder()
                        .setStatus(status)
                        .setPracticeId(practiceId)
                        .build();
                responseObserver.onNext(resp);
                responseObserver.onCompleted();
            }else{
                responseObserver.onError(Status.ALREADY_EXISTS.withDescription("Practice for this financing and user already exists!").asException());
            }

      } else {
        // Handle other actions
        responseObserver.onError(Status.INVALID_ARGUMENT
                .withDescription("Invalid action: only CREATE action is supported")
                .asRuntimeException());
    }
    }


    @Override
    public void updatePractice(Practice request,StreamObserver<PracticeResponse> responseObserver){
       if(request!=null){
           if(dbService.practiceExists(request.getPracticeId())){
                PracticeEntity temp=dbService.getFullPractice(request.getPracticeId());
               AdditionalInfo addInfoTemp=new AdditionalInfo(
                       request.getAdditionalInfo().getJob(),
                       request.getAdditionalInfo().getGender(),
                       LocalDate.of(request.getAdditionalInfo().getDateOfBirth().getYear(),
                       request.getAdditionalInfo().getDateOfBirth().getMonth(),
                               request.getAdditionalInfo().getDateOfBirth().getDay()),
                       request.getAdditionalInfo().getProvince()
               );
               dbService.updatePractice(request.getPracticeId(), addInfoTemp.toString());
               temp.setAdditionalInfo(addInfoTemp.toString());
           }else{
               responseObserver.onError(Status.NOT_FOUND.withDescription("Practice not found!").asRuntimeException());
           }

       }else {
           responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Invalid practice Providede").asRuntimeException());
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
