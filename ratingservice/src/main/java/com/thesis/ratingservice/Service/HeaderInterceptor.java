package com.thesis.ratingservice.Service;

import org.springframework.context.annotation.Profile;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;


@GrpcGlobalServerInterceptor
public class HeaderInterceptor implements ServerInterceptor {
     @Override
        public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
            ServerCall<ReqT, RespT> call,
            Metadata headers,
            ServerCallHandler<ReqT, RespT> next
        ) {
            // Extract and log the headers here
            // Example: Log the Request-ID header
            String requestId = headers.get(Metadata.Key.of("Request-ID", Metadata.ASCII_STRING_MARSHALLER));
            System.out.println(headers.get(Metadata.Key.of("request-id", Metadata.ASCII_STRING_MARSHALLER)));
            // Pass the call to the next interceptor in the chain
            return next.startCall(call, headers);
        }
    
}
