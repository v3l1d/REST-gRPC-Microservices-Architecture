package com.thesis.collectorservice.config;


import brave.grpc.GrpcTracing;
import brave.Tracing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GrpcConfiguration {

    @Bean
    public GrpcTracing grpcTracing(Tracing tracer){return GrpcTracing.create(tracer);}

    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate();

    }
}
