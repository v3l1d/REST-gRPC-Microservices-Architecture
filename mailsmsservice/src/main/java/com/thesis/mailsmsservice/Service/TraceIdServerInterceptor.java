package com.thesis.mailsmsservice.Service;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import brave.grpc.GrpcTracing;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.context.annotation.Profile;


// I wrote this interceptor during the tests for tracing grpc requests, but seems it's not needed because it's just enough intercepting client side
public class TraceIdServerInterceptor implements ServerInterceptor {

    private final GrpcTracing grpcTracing;

    public TraceIdServerInterceptor(GrpcTracing grpcTracing) {
        this.grpcTracing = grpcTracing;
    }

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        return grpcTracing
                .newServerInterceptor()
                .interceptCall(serverCall, metadata, serverCallHandler);
    }
}
