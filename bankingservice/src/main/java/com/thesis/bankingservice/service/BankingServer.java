package com.thesis.bankingservice.service;


import io.grpc.Server;
import io.grpc.ServerBuilder;

public class BankingServer {
    private static final int port=50054;
    private final Server srv;

    public BankingServer() throws Exception{
         srv= ServerBuilder.forPort(port)
            .addService(new BankingServiceImpl())
            .build();
        
    }
    public void start() throws Exception{
        srv.start();
        System.out.println("Server started listening on port:" + port);
        srv.awaitTermination();
    }


    public void stop(){
        srv.shutdown();
    }

}
