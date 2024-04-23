package com.thesis.mailsmsservice.Config;

import brave.Tracing;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import brave.grpc.GrpcTracing;
import brave.grpc.GrpcTracing;
import com.thesis.mailsmsservice.Service.TraceIdServerInterceptor;

@Configuration
public class GrpcConfiguration {
    @Bean
    public GrpcTracing grpcTracing(Tracing tracer) {
        return GrpcTracing.create(tracer);
    }
}
