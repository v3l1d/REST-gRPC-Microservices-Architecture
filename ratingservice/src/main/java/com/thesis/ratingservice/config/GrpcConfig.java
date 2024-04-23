package com.thesis.ratingservice.config;


import brave.Tracing;
import brave.grpc.GrpcTracing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {
    @Bean
    public GrpcTracing grpcTracing(Tracing tracer){
        return GrpcTracing.create(tracer);
    }
}
