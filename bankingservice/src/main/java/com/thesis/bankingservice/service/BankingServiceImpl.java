package com.thesis.bankingservice.service;

import com.thesis.bankingservice.generated.BankingGrpc;
import com.thesis.bankingservice.generated.payRequest;
import com.thesis.bankingservice.generated.payResponse;

import io.grpc.stub.StreamObserver;

public class BankingServiceImpl  extends BankingGrpc.BankingImplBase {

        @Override
        public  void payment(payRequest p, StreamObserver<payResponse> response){
            String result="paymente accepted";
            String requestId=p.getId();
            
            payResponse resp=payResponse.newBuilder()
                .setResult(result)
                .setRequestId(requestId)
                .build();
            response.onNext(resp);
            response.onCompleted();
        }

        
}   
