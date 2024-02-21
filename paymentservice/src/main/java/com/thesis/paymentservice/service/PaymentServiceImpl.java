package com.thesis.paymentservice.service;

import com.thesis.paymentservice.generated.PaymentServiceGrpc;
import com.thesis.paymentservice.generated.Response;
import com.thesis.paymentservice.generated.Payment;
import io.grpc.stub.StreamObserver;

public class PaymentServiceImpl extends PaymentServiceGrpc.PaymentServiceImplBase{
    @Override
    public void payRequest(Payment request, StreamObserver<Response> responseObserver){
        Response resp= Response.newBuilder()
            .setResult("ok")
            .build();
        
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
        
    }
}