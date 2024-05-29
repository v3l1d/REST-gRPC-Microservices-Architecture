package com.thesis.ratingservice.config;
import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class GrpcServerConfig {

    @Bean
    public GrpcServerConfigurer grpcServerConfigurer() {
        return serverBuilder -> serverBuilder
                .keepAliveTime(60, TimeUnit.SECONDS) // Keep-alive time for connections
                .keepAliveTimeout(20, TimeUnit.SECONDS) // Timeout for keep-alive pings
                .permitKeepAliveWithoutCalls(true); // Allow keep-alive pings without active calls
    }
}
