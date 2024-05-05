package com.thesis.bankingservice.config;
import brave.Tracing;
import com.thesis.bankingservice.client.PaymentClientGRPC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import brave.grpc.GrpcTracing;
import org.springframework.context.annotation.Profile;


@Profile("grpc")
@Configuration
public class GrpcConfiguration {
    @Bean
    public GrpcTracing grpcTracing(Tracing tracer) {
        return GrpcTracing.create(tracer);
    }

}
