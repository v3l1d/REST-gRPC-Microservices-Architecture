package com.thesis.ratingservice.Service;

import org.springframework.context.annotation.Profile;

import com.thesis.generated.EvaluationGrpc;
import com.thesis.generated.EvaluationRequest;
import com.thesis.generated.EvaluationResponse;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@Profile("grpc")
@GrpcService
public class EvaluationServiceImpl extends EvaluationGrpc.EvaluationImplBase{
    @Override
    public void evaluate(EvaluationRequest request,StreamObserver<EvaluationResponse> responseObserver){
        if(request.getPractice().getStatus().equals("CREATED")){
            EvaluationResponse response=EvaluationResponse.newBuilder()
                    .setResult("GOOD PRACTICE!")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}