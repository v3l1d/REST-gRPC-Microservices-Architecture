package com.thesis.bankingservice.client;

import brave.grpc.GrpcTracing;
import com.thesis.generated.EvaluationGrpc;
import com.thesis.generated.EvaluationRequest;
import com.thesis.generated.EvaluationResponse;
import com.thesis.generated.Practice;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;

@Profile("grpc")
public class RatingClientGRPC {
private final ManagedChannel chan;
private final Logger logger=LogManager.getLogger(RatingClientGRPC.class);
    public RatingClientGRPC(String host, GrpcTracing grpcTracing){
        this.chan = ManagedChannelBuilder.forTarget(host)
                .usePlaintext()
                .intercept(grpcTracing.newClientInterceptor())
                .build();
    }

    public String getPracticeEvaluation(String practiceId,String reqId){
         Metadata headers = new Metadata();
                Metadata.Key<String> requestIdKey = Metadata.Key.of("Request-ID", Metadata.ASCII_STRING_MARSHALLER);
                headers.put(requestIdKey, reqId);
                logger.info("HEADER:{}",headers);
                EvaluationGrpc.EvaluationBlockingStub stub= EvaluationGrpc.newBlockingStub(chan);
                 stub=stub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(headers));
        Practice toEvaluate=Practice.newBuilder()
            .setPracticeId(practiceId)
            .setStatus("CREATED")
            .build();
        EvaluationRequest request=EvaluationRequest.newBuilder()
                .setPractice(toEvaluate)
                .build();
        EvaluationResponse response=stub.evaluate(request);
        logger.info("Request ID: {} INPUT:{} OUTPUT:{}",reqId,practiceId,response.getResult());
        return response.getResult();
    }



}
