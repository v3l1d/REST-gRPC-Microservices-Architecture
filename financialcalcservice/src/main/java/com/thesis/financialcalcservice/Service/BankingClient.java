package com.thesis.financialcalcservice.Service;


import com.thesis.generated.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.TimeUnit;
import com.thesis.financialcalcservice.model.Customer;
import static com.thesis.generated.Request.Action.CREATE;
import static com.thesis.generated.Request.Action.FILL;

public class BankingClient {
    private final ManagedChannel chan;
    private final BankingGrpc.BankingBlockingStub bankingBlockingStub;

    public BankingClient(String host,int port)  {
        this.chan=ManagedChannelBuilder.forAddress(host,port)
                .usePlaintext()
                .build();
        this.bankingBlockingStub=BankingGrpc.newBlockingStub(chan);

    }

    public void shutdown() throws InterruptedException {
        chan.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
//
    public String createPractice(String customerEmail,String financingId){

        try{
            Practice request= Practice.newBuilder()
                    .setReq(Request.newBuilder().setAction(CREATE))
                    .setEmail(customerEmail)
                    .setFinancingId(financingId)
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

    public void fillPractice(Customer personalData, String practiceId){
        try{
            Practice request=Practice.newBuilder()
                    .setPracticeId(practiceId)
                    .setReq(Request.newBuilder().setAction(FILL))
                    .setName(personalData.getName())
                    .setSurname(personalData.getSurname())
                    .setPhone(personalData.getPhone())
                    .setEmail(personalData.getEmail())
                    .build();
            PracticeResponse resp=bankingBlockingStub.fillPractice(request);
            System.out.println("Practice status: "+resp.getStatus()+ " WITH ID "+resp.getPracticeId());
        }catch (StatusRuntimeException e){
            System.err.println("RPC failed:" + e.getStatus());
        }


    }








}
