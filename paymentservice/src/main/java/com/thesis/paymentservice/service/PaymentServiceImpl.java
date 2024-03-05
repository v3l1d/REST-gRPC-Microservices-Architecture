package com.thesis.paymentservice.service;

import com.thesis.generated.PaymentGrpc;
import com.thesis.generated.PaymentRequest;
import com.thesis.generated.PaymentResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class PaymentServiceImpl extends PaymentGrpc.PaymentImplBase {


    @Override
    public void pay(PaymentRequest request, StreamObserver<PaymentResponse> responseObserver) {

    }


}