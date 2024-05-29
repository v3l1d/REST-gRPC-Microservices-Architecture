package com.thesis.collectorservice.client;


import brave.grpc.GrpcTracing;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.thesis.collectorservice.model.Card;
import com.thesis.collectorservice.model.Transfer;
import com.thesis.generated.AdditionalInfo;
import com.thesis.generated.Date;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import com.thesis.generated.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
@Profile("grpc")
public class BankingClientGRPC {
    private final Logger logger= LogManager.getLogger(BankingClientGRPC.class);
    private final ManagedChannel chan;
    private final BankingGrpc.BankingBlockingStub stub;

    public BankingClientGRPC(String host,GrpcTracing grpcTracing)  {
        this.chan=ManagedChannelBuilder.forTarget(host)
                .usePlaintext()
                .intercept(grpcTracing.newClientInterceptor())
                .keepAliveTime(60, TimeUnit.SECONDS) // Keep-alive time for connections
                .keepAliveTimeout(20, TimeUnit.SECONDS) // Timeout for keep-alive pings
                .keepAliveWithoutCalls(true) // Allow keep-alive pings without active calls
                .usePlaintext() // Use plaintext or TLS based on your setup
                .build();
        this.stub=BankingGrpc.newBlockingStub(chan);
    }

    public boolean insertAdditionalInfo(String practiceId, com.thesis.collectorservice.model.AdditionalInfo addInfo){
        Practice practice=Practice.newBuilder()
                .setPracticeId(practiceId)
                .setAdditionalInfo(AdditionalInfo.newBuilder()
                        .setJob(addInfo.getJob())
                        .setGender(addInfo.getGender())
                        .setProvince(addInfo.getProvince())
                        .setDateOfBirth(Date.newBuilder().
                                setYear(addInfo.getDateOfBirth().getYear())
                                .setMonth(addInfo.getDateOfBirth().getMonthValue())
                                .setDay(addInfo.getDateOfBirth().getDayOfMonth()).build())
                        .build())
                .build();
        PracticeResponse response=stub.addInfoPractice(practice);
        chan.shutdown();
        return response.getStatus().equals("updated");
    }

    public boolean insertCardInfo(String practiceId, Card card) {
        Practice practice = Practice.newBuilder()
                .setPracticeId(practiceId)
                .setPaymentInfo(PaymentInfo.newBuilder()
                        .setCardPayment(
                                CardPaymentInfo.newBuilder()
                                        .setCardNumber(card.getCardNumber())
                                        .setOwner(card.getOwner())
                                        .setCode(card.getCode())
                                        .setExpireDate(Date.newBuilder()
                                                .setYear(card.getExpireDate().getYear())
                                                .setMonth(card.getExpireDate().getMonthValue())
                                                .setDay(card.getExpireDate().getDayOfMonth())
                                                .build())
                        ).
                        build())
                .build();
        PracticeResponse response = stub.payInfoPractice(practice);

        return response.getStatus().equals("updated");
    }

    public boolean insertBtInfo(String practiceId, Transfer transfer){
        Practice practice=Practice.newBuilder()
                .setPracticeId(practiceId)
                .setPaymentInfo(PaymentInfo.newBuilder()
                        .setBankTransfer(BankTransferInfo.newBuilder()
                                .setBankId(transfer.getBankId())
                                .setOwner(transfer.getOwner())
                                .build()))
                .build();
        PracticeResponse response=stub.payInfoPractice(practice);
        return response.getStatus().equals("upadted");
    }

    public boolean personalDocument(String practiceId, com.thesis.collectorservice.model.PersonalDocument personalDocument){
        Practice practice=Practice.newBuilder()
                .setPracticeId(practiceId)
                .setPersonalDocument(PersonalDocument.newBuilder()
                        .setDocumentId(personalDocument.getDocumentId())
                        .setDocumentType(personalDocument.getDocumentType())
                        .setExpireDate(Date.newBuilder()
                                .setYear(personalDocument.getExpireDate().getYear())
                                .setMonth(personalDocument.getExpireDate().getMonthValue())
                                .setDay(personalDocument.getExpireDate().getDayOfMonth())
                                .build())).build();
        BankingGrpc.BankingBlockingStub stub=BankingGrpc.newBlockingStub(chan);
        PracticeResponse response=stub.documentInfoPractice(practice);
        return response.getStatus().equals("updated");
    }

    public boolean creditDocumnet(String practiceId,String creditDocument){
        Practice practice=Practice.newBuilder()
                .setPracticeId(practiceId)
                .build();
        BankingGrpc.BankingBlockingStub stub=BankingGrpc.newBlockingStub(chan);
        PracticeResponse response=stub.creditDocInfoPractice(practice);
        return response.getStatus().equals("updated");
    }

    public PracticeResponse sendToEvaluation(String practiceId, com.thesis.generated.UserData userData) throws InvalidProtocolBufferException {
        Practice practice=Practice.newBuilder()
                .setPracticeId(practiceId)
                .setUserData(userData)
                .build();
        logger.info(practice.getUserData());

        PracticeResponse response=stub.sendToEvaluation(practice);
        return response;
    }

    public String practiceOverview(String practiceId) throws InvalidProtocolBufferException {
        Practice practice=Practice.newBuilder()
                .setPracticeId(practiceId)
                .build();
        Practice response=stub.practiceReview(practice);
        return JsonFormat.printer().print(response);
    }

    public boolean practiceExists(String practiceId){
        Practice practice=Practice.newBuilder()
                .setPracticeId(practiceId)
                .build();
        PracticeResponse response=stub.practiceExists(practice);
        return ("exists").equals(response.getStatus());
    }
    public void shutdown() throws InterruptedException {
        chan.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
//
    public boolean setUserData(UserData userData){
        Practice practice=Practice.newBuilder()
                .setPracticeId("asdasd")
                .setUserData(userData)
                .build();
        PracticeResponse response=stub.setUserData(practice);
        return response.equals("SUCCESS");
    }



    }









