package com.thesis.bankingservice.config;
import io.grpc.ServerBuilder;
import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;


public class GrpcServerConfig {
    public GrpcServerConfigurer grpcServerConfigurer() {
        return serverBuilder -> serverBuilder.permitKeepAliveWithoutCalls(true); // Allow keep-alive pings without active calls
    }
}
