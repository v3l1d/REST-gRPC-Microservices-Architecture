package com.thesis.ratingservice.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.thesis.generated.Practice;
import io.grpc.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;

import com.thesis.generated.EvaluationGrpc;
import com.thesis.generated.EvaluationRequest;
import com.thesis.generated.EvaluationResponse;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@Profile("grpc")
@GrpcService
public class EvaluationServiceImpl extends EvaluationGrpc.EvaluationImplBase{
    private final Logger logger= LogManager.getLogger(EvaluationServiceImpl.class);
    @Override
    public void evaluate(Practice request, StreamObserver<EvaluationResponse> responseObserver){
        if(request.getStatus().equals("COMPLETED")){
            double kpi1 = calculateKPI1(request);
            double kpi2 = calculateKPI2(request);
            double kpi3 = calculateKPI3(request);
            double overallScore = (kpi1 + kpi2 + kpi3) / 3;
            EvaluationResponse response=EvaluationResponse.newBuilder()
                    .setResult(evaluatePractice(overallScore))
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }else {
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Uncompleted practice").asException());
        }
    }
    private double calculateKPI1(Practice request) {
        // Example KPI calculation
        double kpi1 = request.getFinancingInfo().getLoanAmount() / 10000;
        return kpi1 > 10 ? 10 : kpi1; // KPI1 has a maximum score of 10
    }

    private double calculateKPI2(Practice request) {
        // Example KPI calculation
        double kpi2 = request.getFinancingInfo().getLoanTerm() / 12;
        return kpi2 > 10 ? 10 : kpi2; // KPI2 has a maximum score of 10
    }

    private double calculateKPI3(Practice request) {
        // Example KPI calculation
        String province = request.getAdditionalInfo().getProvince().toLowerCase();
        double kpi3 = 0;
        for (char c : province.toCharArray()) {
            kpi3 += c - 'a' + 1; // Assuming 'a' has a value of 1, 'b' has a value of 2, and so on
        }

         return kpi3; // KPI3 has a maximum score of 10
    }

    private String evaluatePractice(double overallScore) {
        if (overallScore >= 8) {
            return "GOOD PRACTICE!";
        } else if (overallScore >= 5) {
            return "AVERAGE PRACTICE!";
        } else {
            return "BELOW AVERAGE PRACTICE!";
        }
    }
}