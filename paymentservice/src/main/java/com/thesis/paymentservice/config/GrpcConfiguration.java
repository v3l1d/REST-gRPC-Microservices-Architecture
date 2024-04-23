package com.thesis.paymentservice.config;

import brave.Tracing;
import brave.grpc.GrpcTracing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfiguration {
    @Bean
    public GrpcTracing grpcTracing(Tracing tracer){
        return GrpcTracing.create(tracer);
    }
}
