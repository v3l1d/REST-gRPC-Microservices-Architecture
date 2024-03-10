package com.thesis.mailsmsservice.Config;

import org.springframework.context.annotation.Profile;
import com.thesis.mailsmsservice.Service.VerifyServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;

@Profile("grpc")
public class GrpcServer {
    private static final int port=50053;
    private final Server srv;
    
    public GrpcServer(){
       srv=ServerBuilder.forPort(port)
            .addService(new VerifyServiceImpl())
            .addService(ProtoReflectionService.newInstance())
            .build();
        
        

    }
    
    public void start() throws Exception{
        srv.start();
        
        System.out.println("Server Stardet on port:" + port + "with services: " + srv.getServices().toString());
    }
    public void stop() throws Exception{
        srv.awaitTermination();
        srv.shutdown();
    }

}
