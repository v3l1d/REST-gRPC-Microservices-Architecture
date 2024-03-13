package com.thesis.financialcalcservice.client;


import com.thesis.generated.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;
import com.thesis.financialcalcservice.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;

@Profile("grpc")
public class BankingClientGRPC {
    private final Logger logger= LogManager.getLogger(BankingClientGRPC.class);

    private final ManagedChannel chan;
    private final BankingGrpc.BankingBlockingStub bankingBlockingStub;

    public BankingClientGRPC(String host, int port)  {
        this.chan=ManagedChannelBuilder.forAddress(host,port)
                .usePlaintext()
                .build();
        this.bankingBlockingStub=BankingGrpc.newBlockingStub(chan);

    }

    public void shutdown() throws InterruptedException {
        chan.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
//
    public String createPractice(Customer personalData,String financingId,double amount){
        logger.info("AMOUNT IN BANKING CLIENT {} : ",amount);
        try{
            Practice request= Practice.newBuilder()
                    .setEmail(personalData.getEmail())
                    .setName(personalData.getName())
                    .setSurname(personalData.getSurname())
                    .setPhone(personalData.getPhone())
                    .setFinancingId(financingId)
                    .setAmount(amount)
                    .build();
            PracticeResponse resp=bankingBlockingStub.createPractice(request);
            if(resp.getStatus().equals("CREATED")){
                return resp.getPracticeId();

            }


        }catch (StatusRuntimeException e){
             System.err.println("RPC failed:" + e.getStatus());
        }
        return null;
    }




    }









