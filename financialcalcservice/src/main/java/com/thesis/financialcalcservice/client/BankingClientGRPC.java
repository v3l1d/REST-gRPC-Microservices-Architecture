package com.thesis.financialcalcservice.client;


import com.thesis.generated.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;
import com.thesis.financialcalcservice.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.thesis.generated.Request.Action.CREATE;
import static com.thesis.generated.Request.Action.FILL;

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
    public String createPractice(String customerEmail,String financingId,double amount){
        logger.info("AMOUNT IN BANKING CLIENT {} : ",amount);
        try{
            Practice request= Practice.newBuilder()
                    .setReq(Request.newBuilder().setAction(CREATE))
                    .setEmail(customerEmail)
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

    public void fillPractice(Customer personalData, String practiceId,double amount){
        try{
            Practice request=Practice.newBuilder()
                    .setPracticeId(practiceId)
                    .setReq(Request.newBuilder().setAction(FILL))
                    .setName(personalData.getName())
                    .setSurname(personalData.getSurname())
                    .setPhone(personalData.getPhone())
                    .setAmount(amount)
                    .setEmail(personalData.getEmail())
                    .build();
            PracticeResponse resp=bankingBlockingStub.fillPractice(request);
            System.out.println("Practice status: "+resp.getStatus()+ " WITH ID "+resp.getPracticeId());
        }catch (StatusRuntimeException e){
            System.err.println("RPC failed:" + e.getStatus());
        }


    }








}
