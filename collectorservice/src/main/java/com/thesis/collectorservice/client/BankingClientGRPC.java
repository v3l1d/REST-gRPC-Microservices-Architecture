package com.thesis.collectorservice.client;


import brave.grpc.GrpcTracing;
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

import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
@Profile("grpc")
public class BankingClientGRPC {
    private final Logger logger= LogManager.getLogger(BankingClientGRPC.class);

    private final ManagedChannel chan;

    public BankingClientGRPC(String host,GrpcTracing grpcTracing)  {
        this.chan=ManagedChannelBuilder.forTarget(host)
                .usePlaintext()
                .intercept(grpcTracing.newClientInterceptor())
                .build();

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
        BankingGrpc.BankingBlockingStub stub=BankingGrpc.newBlockingStub(chan);
        PracticeResponse response=stub.addInfoPractice(practice);
        if(response.getStatus().equals("updated")){
            return true;
        }else {
            return false;

        }
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
        BankingGrpc.BankingBlockingStub stub = BankingGrpc.newBlockingStub(chan);
        PracticeResponse response = stub.payInfoPractice(practice);
        if (response.getStatus().equals("updated")) {
            return true;
        } else {
            return false;

        }
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
        BankingGrpc.BankingBlockingStub stub=BankingGrpc.newBlockingStub(chan);
        PracticeResponse response=stub.payInfoPractice(practice);
        if(response.getStatus().equals("updated")){
            return true;
        }else{
            return false;
        }
    }


    public String sendToEvaluation(String practiceId){
        Practice practice=Practice.newBuilder()
                .setPracticeId(practiceId)
                .build();
        BankingGrpc.BankingBlockingStub stub=BankingGrpc.newBlockingStub(chan);
        PracticeResponse response=stub.sendToEvaluation(practice);

        return response.getEvaluationResult();

    }
    public void shutdown() throws InterruptedException {
        chan.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
//


    }









