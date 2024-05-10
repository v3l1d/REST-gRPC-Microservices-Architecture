package com.thesis.financialcalcservice.client;


import com.thesis.financialcalcservice.model.Financing;
import com.thesis.financialcalcservice.model.Vehicle;
import com.thesis.generated.*;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.MetadataUtils;
import brave.grpc.GrpcTracing;
import java.util.concurrent.TimeUnit;
import com.thesis.financialcalcservice.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;

@SuppressWarnings("unused")
@Profile("grpc")
public class BankingClientGRPC {
    private final Logger logger= LogManager.getLogger(BankingClientGRPC.class);

    private final ManagedChannel chan;
    private final BankingGrpc.BankingBlockingStub stub;
    public BankingClientGRPC(String host,GrpcTracing grpcTracing)  {
        this.chan=ManagedChannelBuilder.forTarget(host)
                .usePlaintext()
                .intercept(grpcTracing.newClientInterceptor())
                .build();
        this.stub=BankingGrpc.newBlockingStub(chan);
    }

    public void shutdown() throws InterruptedException {
        chan.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
//
    public String createPractice(Customer personalData, Financing financing, Vehicle vehicleTemp){

        try{

            Practice request= Practice.newBuilder()
                    .setEmail(personalData.getEmail())
                    .setName(personalData.getName())
                    .setSurname(personalData.getSurname())
                    .setPhone(personalData.getPhone())
                    .setFinancingInfo(FinancingInfo.newBuilder()
                            .setFinancingId(financing.getFinancingId())
                            .setVehicleId(financing.getVehicleId())
                            .setLoanAmount(financing.getLoanAmount())
                            .setLoanTerm(financing.getLoanTerm())
                            .build())
                    .setVehicleInfo(VehicleInfo.newBuilder()
                            .setVehicleId(vehicleTemp.getVehicleId())
                            .setBrand(vehicleTemp.getBrand())
                            .setName(vehicleTemp.getModel())
                            .setYear(vehicleTemp.getYear()))
                    .build();
            PracticeResponse resp=stub.createPractice(request);
    
            logger.info("got this repsonse: {} ", resp.getPracticeId());
            if(resp.getStatus().equals("CREATED")){
                
                return resp.getPracticeId();

            }


        }catch (StatusRuntimeException e){
             System.err.println("RPC failed:" + e.getStatus());
        }
        return null;
    }




    }









