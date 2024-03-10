package com.thesis.bankingservice.service;


import com.thesis.bankingservice.repository.PracticeRepository;

import io.grpc.Server;
import io.grpc.ServerBuilder;


/**THIS SERVER IMPLEMENTATION IS NOT USED BECAUSE I PASSED TO @GRPCService from spring-boot-starter library */
public class BankingServer {
    private static final int port=50054;
    private final Server srv;

    
    public BankingServer(PracticeRepository practiceRepository) throws Exception{
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
