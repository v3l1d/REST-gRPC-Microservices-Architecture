package com.thesis.bankingservice.client;

import brave.grpc.GrpcTracing;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.bankingservice.model.AdditionalInfo;
import com.thesis.bankingservice.model.PracticeEntity;
import com.thesis.bankingservice.service.BankDBService;
import com.thesis.generated.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Profile("grpc")
public class RatingClientGRPC {
private final ManagedChannel chan;
private final Logger logger=LogManager.getLogger(RatingClientGRPC.class);
private final EvaluationGrpc.EvaluationBlockingStub stub;
    public RatingClientGRPC(String host, GrpcTracing grpcTracing){

        this.chan = ManagedChannelBuilder.forTarget(host)
                .usePlaintext()
                .intercept(grpcTracing.newClientInterceptor())
                .keepAliveTime(60, TimeUnit.SECONDS) // Keep-alive time for connections
                .keepAliveTimeout(20, TimeUnit.SECONDS) // Timeout for keep-alive pings
                .keepAliveWithoutCalls(true) // Allow keep-alive pings without active calls
                .usePlaintext() // Use plaintext or TLS based on your setup
                .build();
        stub=EvaluationGrpc.newBlockingStub(chan);
    }

    /*
    public String getPracticeEvaluation(PracticeEntity practice) throws JsonProcessingException {
        ObjectMapper obj=new ObjectMapper();
        EvaluationGrpc.EvaluationBlockingStub stub= EvaluationGrpc.newBlockingStub(chan);
        logger.info(practice.getAdditionalInfo());
        JsonNode info=obj.readTree(practice.getAdditionalInfo());
        JsonNode financingInfo=obj.readTree(practice.getFinancingInfo());
        JsonNode vehicleInfo=obj.readTree(practice.getVehicleInfo());

       AdditionalInfo addInfoTemp=new AdditionalInfo(info.get("job").asText(),
                info.get("gender").asText(),
                LocalDate.parse(info.get("dateOfBirth").asText(),DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                info.get("province").asText());

        com.thesis.generated.AdditionalInfo addInfo= com.thesis.generated.AdditionalInfo.newBuilder()
                .setDateOfBirth(Date.newBuilder()
                        .setYear(addInfoTemp.getDateOfBirth().getYear())
                        .setMonth(addInfoTemp.getDateOfBirth().getMonthValue())
                        .setDay(addInfoTemp.getDateOfBirth().getDayOfMonth()))
                .setGender(addInfoTemp.getGender())
                .setJob(addInfoTemp.getJob())
                .setProvince(addInfoTemp.getProvince())
                .build();
        FinancingInfo finTemp=FinancingInfo.newBuilder()
                .setFinancingId(financingInfo.get("financingId").asText())
                .setVehicleId(financingInfo.get("vehicleId").asText())
                .setLoanTerm(financingInfo.get("loanTerm").asDouble())
                .setLoanAmount(financingInfo.get("loanAmount").asDouble())
                .build();
        VehicleInfo vehicleTemp=VehicleInfo.newBuilder()
                        .setVehicleId(vehicleInfo.get("vehicleId").asText())
                        .setName(vehicleInfo.get("model").asText())
                        .setBrand(vehicleInfo.get("brand").asText())
                        .setYear(vehicleInfo.get("year").asInt()).build();


        logger.info("{},{},{}",vehicleTemp,finTemp,addInfo);
       Practice toEvaluate=Practice.newBuilder()
            .setPracticeId(practice.getPracticeId())
            .setStatus("COMPLETED")
               .setVehicleInfo(vehicleTemp)
               .setFinancingInfo(finTemp)
               .setAdditionalInfo(addInfo)
            .build();
       logger.info(toEvaluate);
        EvaluationResponse response=stub.evaluate(toEvaluate);
        logger.info("INPUT:{} OUTPUT:{}",toEvaluate);
        return response.getResult();
    }
*/
    public String getPracticeEvaluation(Practice practice){
        EvaluationResponse response=stub.evaluate(practice);
        return response.getResult();
    }
}
