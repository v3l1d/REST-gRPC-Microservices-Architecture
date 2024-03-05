package com.thesis.ratingservice.Service;

import com.thesis.generated.EvaluationGrpc;
import com.thesis.generated.EvaluationRequest;
import com.thesis.generated.EvaluationResponse;

import io.grpc.stub.StreamObserver;

public class EvaluationServiceImpl extends EvaluationGrpc.EvaluationImplBase{
    @Override
    public void evaluate(EvaluationRequest request,StreamObserver<EvaluationResponse> responseObserver){
        if(request.getPractice().getStatus().equals("COMPLETED")){
            EvaluationResponse response=EvaluationResponse.newBuilder()
                    .setResult("GOOD PRACTICE!")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}