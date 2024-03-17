package com.thesis.bankingservice.client;

import com.thesis.bankingservice.model.PracticeEntity;
import com.thesis.generated.EvaluationGrpc;
import com.thesis.generated.EvaluationRequest;
import com.thesis.generated.EvaluationResponse;
import com.thesis.generated.Practice;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.thesis.generated.EvaluationGrpc.EvaluationBlockingStub;
import org.springframework.context.annotation.Profile;

@Profile("grpc")
public class RatingClientGRPC {
private final ManagedChannel chan;
private final EvaluationBlockingStub stub;
    public RatingClientGRPC(String host){
        this.chan = ManagedChannelBuilder.forTarget(host)
                .usePlaintext()
                .build();
        this.stub = EvaluationGrpc.newBlockingStub(chan);
    }

    public String getPracticeEvaluation(String practiceId){
        Practice toEvaluate=Practice.newBuilder()
            .setPracticeId(practiceId)
            .setStatus("CREATED")
            .build();
        EvaluationRequest request=EvaluationRequest.newBuilder()
                .setPractice(toEvaluate)
                .build();
        EvaluationResponse response=stub.evaluate(request);
        return response.getResult();
    }



}
