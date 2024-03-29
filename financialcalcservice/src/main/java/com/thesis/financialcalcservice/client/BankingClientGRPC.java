package com.thesis.financialcalcservice.client;


import com.thesis.generated.*;
import com.thesis.generated.BankingGrpc.BankingBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.MetadataUtils;

import java.util.concurrent.TimeUnit;
import com.thesis.financialcalcservice.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;

@SuppressWarnings("unused")
@Profile("grpc")
public class BankingClientGRPC {
    private final Logger logger= LogManager.getLogger(BankingClientGRPC.class);

    private final ManagedChannel chan;

    public BankingClientGRPC(String host)  {
        this.chan=ManagedChannelBuilder.forTarget(host)
                .usePlaintext()
                .build();

    }

    public void shutdown() throws InterruptedException {
        chan.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
//
    public String createPractice(Customer personalData,String financingId,double amount,String reqId){
        logger.info("AMOUNT IN BANKING CLIENT {} : ",amount);
        try{
             Metadata headers = new Metadata();
        // Add the Request-ID header to the Metadata object
        Metadata.Key<String> requestIdKey = Metadata.Key.of("Request-ID", Metadata.ASCII_STRING_MARSHALLER);
        headers.put(requestIdKey, reqId);
        
        BankingGrpc.BankingBlockingStub stub= BankingGrpc.newBlockingStub(chan);
        stub=stub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(headers));
            Practice request= Practice.newBuilder()
                    .setEmail(personalData.getEmail())
                    .setName(personalData.getName())
                    .setSurname(personalData.getSurname())
                    .setPhone(personalData.getPhone())
                    .setFinancingId(financingId)
                    .setAmount(amount)
                    .build();
            PracticeResponse resp=stub.createPractice(request);
    
            logger.info("Request id: {} got this repsonse: {} ", reqId, resp.getPracticeId());
            if(resp.getStatus().equals("CREATED")){
                
                return resp.getPracticeId();

            }


        }catch (StatusRuntimeException e){
             System.err.println("RPC failed:" + e.getStatus());
        }
        return null;
    }




    }









