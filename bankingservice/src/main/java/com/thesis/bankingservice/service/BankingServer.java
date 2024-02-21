package com.thesis.bankingservice.service;


import io.grpc.Server;
import io.grpc.ServerBuilder;

public class BankingServer {
    private static final int port=50053;

    public BankingServer() throws Exception{
        Server srv= ServerBuilder.forPort(port)
            .addService(new BankingServiceImpl())
            .build();
        srv.start();
        System.out.println("Server started listening on port:" + port);
        srv.awaitTermination();
    }


}
