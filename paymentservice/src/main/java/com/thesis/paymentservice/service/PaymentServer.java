package com.thesis.paymentservice.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import com.thesis.paymentservice.service.PaymentServiceImpl;


public class PaymentServer {
    private static final int port=50051;
    private final Server srv;

    public PaymentServer() throws Exception{
        srv=ServerBuilder.forPort(port)
            .addService(new PaymentServiceImpl())
            .build();
        System.out.println("Sever starting on port " + port);
        srv.start();
        srv.awaitTermination();
        
    }


    
}
