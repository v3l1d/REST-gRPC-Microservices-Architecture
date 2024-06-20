package com.thesis.bankingservice.service;

import brave.grpc.GrpcTracing;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.thesis.bankingservice.client.MailSMSClientGRPC;
import com.thesis.bankingservice.client.PaymentClientGRPC;
import com.thesis.bankingservice.client.RatingClientGRPC;
import com.thesis.bankingservice.model.AdditionalInfo;
import com.thesis.bankingservice.model.PersonalDocument;
import com.thesis.bankingservice.model.*;
import com.thesis.generated.*;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Profile("grpc")
@GrpcService
public class BankingServiceImpl  extends BankingGrpc.BankingImplBase {
    private final Logger logger = LogManager.getLogger(BankingServiceImpl.class);

    private BankDBService dbService;
    private String tempId;

    private final PaymentClientGRPC paymentClientGRPC;

    private final RatingClientGRPC ratingClientGRPC;

    private final MailSMSClientGRPC mailSMSClientGRPC;
    private ObjectMapper obj=new ObjectMapper();



    @Autowired

    public BankingServiceImpl(@Value("${paymentservice.grpc.url}") String paymentServerGrpcUrl,

                              @Value("${ratingservice.grpc.url}") String ratingServerGrpcUrl,

                              @Value("${mailsmsservice.grpc.url}")  String mailSMSServerGrpcUrl,

                              BankDBService dbService, GrpcTracing grpcTracing,ObjectMapper obj) {

        this.dbService = dbService;

        this.paymentClientGRPC = new PaymentClientGRPC(paymentServerGrpcUrl, grpcTracing);

        this.ratingClientGRPC = new RatingClientGRPC(ratingServerGrpcUrl, grpcTracing);

        this.mailSMSClientGRPC=new MailSMSClientGRPC(mailSMSServerGrpcUrl,grpcTracing);

        this.obj=obj;
    }


    @Override
    public void createPractice(Practice request, StreamObserver<PracticeResponse> responseObserver) {

        if (request != null) {
            if (!dbService.practiceExists(request.getPracticeId())) {
                String status = "CREATED";
                tempId = practiceIdGen();
                String practiceId = tempId;
                PracticeEntity entity = new PracticeEntity();
                entity.setStatus(status);
                entity.setPracticeId(practiceId);
                entity.setEmail(request.getEmail());
                entity.setPhone(request.getPhone());
                entity.setName(request.getName());
                entity.setSurname(request.getSurname());
                Financing finTemp = new Financing(request.getFinancingInfo().getFinancingId(),
                        request.getFinancingInfo().getVehicleId(),
                        request.getFinancingInfo().getLoanAmount(),
                        request.getFinancingInfo().getLoanTerm());
                entity.setFinancingInfo(finTemp);
                Vehicle vehicleTemp = new Vehicle(request.getVehicleInfo().getVehicleId(),
                        request.getVehicleInfo().getBrand(),
                        request.getVehicleInfo().getModel(),
                        request.getVehicleInfo().getYear());
                entity.setVehicleInfo(vehicleTemp);

                dbService.newPractice(entity);
                PracticeResponse resp = PracticeResponse.newBuilder()
                        .setStatus(status)
                        .setPracticeId(practiceId)
                        .build();
                responseObserver.onNext(resp);
                responseObserver.onCompleted();
            } else {
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
    public void addInfoPractice(Practice request, StreamObserver<PracticeResponse> responseObserver) {
        PracticeEntity temp = dbService.getFullPractice(request.getPracticeId());
        if (temp != null) {
            AdditionalInfo addInfoTemp = new AdditionalInfo(
                    request.getAdditionalInfo().getJob(),
                    request.getAdditionalInfo().getGender(),
                    LocalDate.parse(request.getAdditionalInfo().getDateOfBirth(),DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                    request.getAdditionalInfo().getProvince());
            dbService.updatePractice(request.getPracticeId(), addInfoTemp);
            PracticeResponse resp = PracticeResponse.newBuilder()
                    .setPracticeId(request.getPracticeId())
                    .setStatus("updated").build();
            responseObserver.onNext(resp);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(Status.NOT_FOUND.withDescription("Practice NOT FOUND").asException());
        }

    }


    @Override
    public void payInfoPractice(Practice request, StreamObserver<PracticeResponse> responseObserver) {
        PracticeEntity temp = dbService.getFullPractice(request.getPracticeId());
        if (request.getPaymentInfo().hasBankTransfer()) {
            Transfer transferTemp = new Transfer(
                    request.getPaymentInfo().getBankTransfer().getOwner(),
                    request.getPaymentInfo().getBankTransfer().getBankId()
            );
            String paymentResp = paymentClientGRPC.paymentRequestTransfer(BankPayment.newBuilder().setTransfer(request.getPaymentInfo().getBankTransfer()).build());
            if (paymentResp.equals("ACCEPTED")) {
                dbService.setPaymentMethod(request.getPracticeId(), "transfer", transferTemp.toString());
                dbService.setPracticeToCompleted(request.getPracticeId());
                PracticeResponse resp;
                resp = PracticeResponse.newBuilder()
                        .setStatus("updated")
                        .setPracticeId(request.getPracticeId()).build();
                responseObserver.onNext(resp);
                responseObserver.onCompleted();
            } else {
                responseObserver.onError(Status.PERMISSION_DENIED.withDescription("Payment refused!").asRuntimeException());
            }
        }

        if (request.getPaymentInfo().hasCardPayment()) {
            CardPaymentInfo cardPaymentInfo = request.getPaymentInfo().getCardPayment();
            String paymentResp = paymentClientGRPC.paymentRequestCard(CardPayment.newBuilder().setCard(cardPaymentInfo).build());
            if (paymentResp.equals("ACCEPTED")) {
                Card cardTemp = new Card(
                        request.getPaymentInfo().getCardPayment().getOwner(),
                        request.getPaymentInfo().getCardPayment().getCardNumber(),
                        request.getPaymentInfo().getCardPayment().getCode(),
                        LocalDate.parse(request.getPaymentInfo().getCardPayment().getExpireDate(),DateTimeFormatter.ofPattern("yyyy-MM-dd"))

                );
                dbService.setPaymentMethod(request.getPracticeId(), "card", cardTemp.toString());
                dbService.setPracticeToCompleted(request.getPracticeId());
                PracticeResponse resp;
                resp = PracticeResponse.newBuilder()
                        .setStatus("updated")
                        .setPracticeId(request.getPracticeId()).build();
                responseObserver.onNext(resp);
                responseObserver.onCompleted();
            } else {
                responseObserver.onError(Status.PERMISSION_DENIED.withDescription("PaymentRefused").asRuntimeException());
            }

        }


    }

    /*
    @Override
    public void sendToEvaluation(Practice request, StreamObserver<PracticeResponse> responseObserver) {
        if (dbService.practiceExists(request.getPracticeId())) {
            PracticeEntity temp = dbService.getFullPractice(request.getPracticeId());
            try {
                logger.info(temp);
                if (temp.getAdditionalInfo() != null && temp.getAdditionalInfo() != null && temp.getFinancingInfo() != null && temp.getFinancingInfo() != null) {
                    String response = ratingClientGRPC.getPracticegEvaluation(temp);
                    PracticeResponse evalResp = PracticeResponse.newBuilder()
                            .setPracticeId(request.getPracticeId())
                            .setStatus(temp.getStatus())
                            .setEvaluationResult(response)
                            .build();
                    logger.info(evalResp);
                    responseObserver.onNext(evalResp);
                    responseObserver.onCompleted();
                } else {
                    responseObserver.onError(Status.FAILED_PRECONDITION.withDescription("Missing information in practice").asRuntimeException());
                }

            } catch (JsonProcessingException e) {
                throw new RuntimeException();
            }

        }
    }
    */

    @Override
    public void sendToEvaluation(Practice request,StreamObserver<PracticeResponse> responseObserver)  {
        if(dbService.practiceExists(request.getPracticeId())){
            PracticeEntity practice=dbService.getFullPractice(request.getPracticeId());
            FinancingInfo.Builder finBuilder=FinancingInfo.newBuilder();
            VehicleInfo.Builder vehicleBuilder=VehicleInfo.newBuilder();
            UserData.Builder userDataBuilder=UserData.newBuilder();
            com.thesis.generated.AdditionalInfo.Builder addInfoBuilder= com.thesis.generated.AdditionalInfo.newBuilder();
            try {
                String financingInfoJson=obj.writeValueAsString(practice.getFinancingInfo());
                JsonFormat.parser().merge(financingInfoJson,finBuilder);
                String vehicleInfoJson=obj.writeValueAsString(practice.getVehicleInfo());
                JsonFormat.parser().merge(vehicleInfoJson,vehicleBuilder);
                String userDataString=obj.writeValueAsString(practice.getUserData());
                JsonFormat.parser().merge(userDataString,userDataBuilder);
                VehicleInfo vehicleInfo=vehicleBuilder.build();
                FinancingInfo financingInfo=finBuilder.build();
                String addInfoString=obj.writeValueAsString(practice.getAdditionalInfo());
                logger.info(addInfoString);
                UserData userData=userDataBuilder.build();
                Practice toEvaluate=Practice.newBuilder()
                        .setPracticeId(request.getPracticeId())
                        .setStatus("COMPLETED")
                        .setUserData(userData)
                        .setFinancingInfo(financingInfo)
                        .setVehicleInfo(vehicleInfo)
                        .build();
                logger.info(toEvaluate.getStatus());
                String response = ratingClientGRPC.getPracticeEvaluation(toEvaluate);
                PracticeResponse evalResp = PracticeResponse.newBuilder()
                        .setPracticeId(request.getPracticeId())
                        .setStatus(toEvaluate.getStatus())
                        .setPractice(toEvaluate)
                        .setEvaluationResult(response)
                        .build();
                logger.info(evalResp);
                responseObserver.onNext(evalResp);
                responseObserver.onCompleted();
            } catch (InvalidProtocolBufferException | JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }else{
            responseObserver.onError(Status.NOT_FOUND.withDescription("practice not found").asException());
        }
    }
    @Override
    public void documentInfoPractice(Practice request, StreamObserver<PracticeResponse> responseObserver) {
        if (dbService.practiceExists(request.getPracticeId())) {
            PersonalDocument temp = new PersonalDocument(
                    request.getPersonalDocument().getDocumentId(),
                    request.getPersonalDocument().getDocumentType(),
                    LocalDate.parse(request.getPersonalDocument().getExpireDate(),DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            );
            logger.info(temp);
            dbService.setPersonalDocument(request.getPracticeId(), temp);
            PracticeResponse response = PracticeResponse.newBuilder()
                    .setPracticeId(request.getPracticeId())
                    .setStatus("updated")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(Status.FAILED_PRECONDITION.withDescription("Practice don't exists!").asRuntimeException());
        }

    }

    @Override
    public void creditDocInfoPractice(Practice request, StreamObserver<PracticeResponse> responseObserver) {
        if (dbService.practiceExists(request.getPracticeId())) {
            dbService.setCreditDocument(request.getPracticeId(), request.getCreditDocument());
            PracticeResponse response = PracticeResponse.newBuilder()
                    .setPracticeId(request.getPracticeId())
                    .setStatus("updated")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(Status.INVALID_ARGUMENT.asRuntimeException());
        }
    }

    @Override
    public void practiceExists(Practice request, StreamObserver<PracticeResponse> responseStreamObserver) {
        if (dbService.practiceExists(request.getPracticeId())) {
            PracticeResponse response = PracticeResponse.newBuilder()
                    .setPracticeId(request.getPracticeId())
                    .setStatus("exists")
                    .build();
            responseStreamObserver.onNext(response);
            responseStreamObserver.onCompleted();
        } else {
            PracticeResponse response = PracticeResponse.newBuilder()
                    .setPracticeId(request.getPracticeId())
                    .setStatus("notfound")
                    .build();
            responseStreamObserver.onNext(response);
            responseStreamObserver.onCompleted();
        }
    }

    @Override
    public void setUserData(Practice request,StreamObserver<PracticeResponse> responseObserver)  {
    UserData userData = request.getUserData();
        try {
            if (dbService.practiceExists(request.getPracticeId())) {
                String UserData = JsonFormat.printer().print(request.getUserData());
                logger.info(userData);
                ObjectMapper mapper=new ObjectMapper();
                com.thesis.bankingservice.model.UserDataModels.model.UserData temp=mapper.readValue(UserData, com.thesis.bankingservice.model.UserDataModels.model.UserData.class);
                dbService.setUserData(request.getPracticeId(), temp);
                PracticeResponse response = PracticeResponse.newBuilder()
                        .setPracticeId(request.getPracticeId())
                        .setStatus("SUCCESS")
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                responseObserver.onError(Status.NOT_FOUND.withDescription("Practice don't exists").asException());
            }
        }catch (InvalidProtocolBufferException e){
            responseObserver.onError(Status.INTERNAL.withDescription("Error processing UserData").asException());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void practiceReview(Practice request, StreamObserver<Practice> responseObserver) {
        if(dbService.practiceExists(request.getPracticeId())){
            try {
                ObjectMapper mapper = new ObjectMapper();
                PracticeEntity temp = dbService.getFullPractice(request.getPracticeId());
                AdditionalInfo additionalInfo = temp.getAdditionalInfo();
                Financing financing = temp.getFinancingInfo();
                com.thesis.bankingservice.model.UserDataModels.model.UserData udata = temp.getUserData();
                Vehicle vehicle = temp.getVehicleInfo();
                String addInfoString = mapper.writeValueAsString(additionalInfo);
                String vehicleInfo = mapper.writeValueAsString(vehicle);
                String financingInfo = mapper.writeValueAsString(financing);
                String udataString = mapper.writeValueAsString(udata);

                VehicleInfo.Builder vehicleBuilder = VehicleInfo.newBuilder();
                FinancingInfo.Builder finBuilder = FinancingInfo.newBuilder();
                UserData.Builder udataBuilder = UserData.newBuilder();
                JsonFormat.parser().merge(financingInfo, finBuilder);
                JsonFormat.parser().merge(vehicleInfo, vehicleBuilder);
                JsonFormat.parser().merge(udataString, udataBuilder);

                VehicleInfo vehicleInfo1 = vehicleBuilder.build();
                FinancingInfo finInfo = finBuilder.build();
                UserData userData = udataBuilder.build();

                Practice toOverview = Practice.newBuilder()
                        .setPracticeId(request.getPracticeId())
                        .setPhone(temp.getPhone())
                        .setEmail(temp.getEmail())
                        .setName(temp.getName())
                        .setSurname(temp.getSurname())
                        .setStatus(temp.getStatus())
                        .setVehicleInfo(vehicleInfo1)
                        .setFinancingInfo(finInfo)
                        .setUserData(userData)
                        .build();

                Practice response=mailSMSClientGRPC.practiceOverview(toOverview);
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } catch (InvalidProtocolBufferException e) {
                throw new RuntimeException(e);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }else {
            responseObserver.onError(Status.NOT_FOUND.withDescription("Practice not found!").asException());
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



        

