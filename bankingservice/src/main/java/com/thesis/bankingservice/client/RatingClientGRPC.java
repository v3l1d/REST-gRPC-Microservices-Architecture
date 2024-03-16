package com.thesis.bankingservice.client;

import com.thesis.generated.EvaluationGrpc;
import com.thesis.generated.EvaluationRequest;
import com.thesis.generated.EvaluationResponse;
import com.thesis.generated.Practice;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.thesis.generated.EvaluationGrpc.EvaluationBlockingStub;
import org.springframework.context.annotation.Profile;

@Profile("grpc")
public class RatingClient {
private final ManagedChannel chan;
private final EvaluationBlockingStub stub;
    private final String host="localhost";
    private final int port= 50055;
    public RatingClient(String host,int port){
        this.chan = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        this.stub = EvaluationGrpc.newBlockingStub(chan);
    }

    public String getPracticeEvaluation(Practice practice){
        EvaluationRequest request=EvaluationRequest.newBuilder()
                .setPractice(practice)
                .build();
        EvaluationResponse response=stub.evaluate(request);
        return response.getResult();
    }



}
