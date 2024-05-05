package com.thesis.bankingservice.service;

import java.time.LocalDate;
import java.util.Random;

import brave.grpc.GrpcTracing;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.thesis.bankingservice.client.PaymentClientGRPC;
import com.thesis.bankingservice.client.RatingClientGRPC;
import com.thesis.bankingservice.model.*;
import com.thesis.bankingservice.model.AdditionalInfo;
import com.thesis.generated.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;

@Profile("grpc")
@GrpcService(interceptors = {HeaderInterceptor.class})
public class BankingServiceImpl  extends BankingGrpc.BankingImplBase {
    private final Logger logger=LogManager.getLogger(BankingServiceImpl.class);

    private BankDBService dbService;
    private String tempId;

    private final PaymentClientGRPC paymentClientGRPC;

    private final RatingClientGRPC ratingClientGRPC;


    @Autowired

    public BankingServiceImpl(@Value("${paymentservice.grpc.url}") String paymentServerGrpcUrl,

                              @Value("${ratingservice.grpc.url}") String ratingServerGrpcUrl,

                              BankDBService dbService, GrpcTracing grpcTracing) {

        this.dbService = dbService;

        this.paymentClientGRPC = new PaymentClientGRPC(paymentServerGrpcUrl, grpcTracing);

        this.ratingClientGRPC = new RatingClientGRPC(ratingServerGrpcUrl, grpcTracing);

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
    public void addInfoPractice(Practice request,StreamObserver<PracticeResponse> responseObserver){
       PracticeEntity temp=dbService.getFullPractice(request.getPracticeId());
       if(temp!=null){
           AdditionalInfo addInfoTemp= new AdditionalInfo(
                   request.getAdditionalInfo().getJob(),
                   request.getAdditionalInfo().getGender(),
                   LocalDate.of(
                           request.getAdditionalInfo().getDateOfBirth().getYear(),
                           request.getAdditionalInfo().getDateOfBirth().getMonth(),
                           request.getAdditionalInfo().getDateOfBirth().getDay()
                   ),
                   request.getAdditionalInfo().getProvince());
           dbService.updatePractice(request.getPracticeId(),addInfoTemp.toString());
           PracticeResponse resp=PracticeResponse.newBuilder()
                   .setPracticeId(request.getPracticeId())
                   .setStatus("updated").build();
           responseObserver.onNext(resp);
           responseObserver.onCompleted();
       }else {
           responseObserver.onError(Status.NOT_FOUND.withDescription("Practice NOT FOUND").asRuntimeException());
       }

    }



    @Override
    public void payInfoPractice(Practice request,StreamObserver<PracticeResponse> responseObserver){
            PracticeEntity temp=dbService.getFullPractice(request.getPracticeId());

            if(request.getPaymentInfo().hasBankTransfer()){

                Transfer transferTemp=new Transfer(
                        request.getPaymentInfo().getBankTransfer().getOwner(),
                        request.getPaymentInfo().getBankTransfer().getBankId()
                );
                String paymentResp=paymentClientGRPC.paymentRequestTransfer(BankPayment.newBuilder().setTransfer(request.getPaymentInfo().getBankTransfer()).build());
                if(paymentResp.equals("ACCEPTED")){
                    dbService.setPaymentMethod(request.getPracticeId(),"transfer",transferTemp.toString());
                    dbService.setPracticeToCompleted(request.getPracticeId());
                    PracticeResponse resp;
                    resp=PracticeResponse.newBuilder()
                            .setStatus("updated")
                            .setPracticeId(request.getPracticeId()).build();
                    responseObserver.onNext(resp);
                    responseObserver.onCompleted();
                }else{
                    responseObserver.onError(Status.PERMISSION_DENIED.withDescription("Payment refused!").asRuntimeException());
                }
            }

            if(request.getPaymentInfo().hasCardPayment()){
                CardPaymentInfo cardPaymentInfo=request.getPaymentInfo().getCardPayment();
                String paymentResp=paymentClientGRPC.paymentRequestCard(CardPayment.newBuilder().setCard(cardPaymentInfo).build());
                if(paymentResp.equals("ACCEPTED")){
                    Card cardTemp= new Card(
                            request.getPaymentInfo().getCardPayment().getOwner(),
                            request.getPaymentInfo().getCardPayment().getCardNumber(),
                            request.getPaymentInfo().getCardPayment().getCode(),
                            LocalDate.of(
                                    request.getPaymentInfo().getCardPayment().getExpireDate().getYear(),
                                    request.getPaymentInfo().getCardPayment().getExpireDate().getMonth(),
                                    request.getPaymentInfo().getCardPayment().getExpireDate().getDay()
                            )
                    );
                    dbService.setPaymentMethod(request.getPracticeId(),"card",cardTemp.toString());
                    dbService.setPracticeToCompleted(request.getPracticeId());
                    PracticeResponse resp;
                    resp=PracticeResponse.newBuilder()
                            .setStatus("updated")
                            .setPracticeId(request.getPracticeId()).build();
                    responseObserver.onNext(resp);
                    responseObserver.onCompleted();
                }else{
                    responseObserver.onError(Status.PERMISSION_DENIED.withDescription("PaymentRefused").asRuntimeException());
                }

            }


    }

    @Override
    public void sendToEvaluation(Practice request,StreamObserver<PracticeResponse> responseObserver){
        if(dbService.practiceExists(request.getPracticeId())){
            PracticeEntity temp=dbService.getFullPractice(request.getPracticeId());
            try {
                logger.info(temp);
                String response=ratingClientGRPC.getPracticeEvaluation(temp);
                PracticeResponse evalResp=PracticeResponse.newBuilder()
                        .setPracticeId(request.getPracticeId())
                        .setStatus(temp.getStatus())
                        .setEvaluationResult(response)
                        .build();
                logger.info(evalResp);
                responseObserver.onNext(evalResp);
                responseObserver.onCompleted();
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }
    }

 /*   @Override
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
    } */






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
