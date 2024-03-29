package com.thesis.bankingservice.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;

@Profile("grpc")
@GrpcGlobalClientInterceptor
public class HeaderInterceptor implements ServerInterceptor  {
    private final Logger logger=LogManager.getLogger(HeaderInterceptor.class);

    @Override
        public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            Metadata headers,
            ServerCallHandler<ReqT, RespT> next
        ) {
            // Extract and log the headers here
            // Example: Log the Request-ID header
            String requestId = headers.get(Metadata.Key.of("Request-ID", Metadata.ASCII_STRING_MARSHALLER));
            System.out.println("Request ID: "+ requestId);
            logger.info("Request ID:{}",requestId);

            // Pass the call to the next interceptor in the chain
            return next.startCall(call, headers);
        }
    
}
