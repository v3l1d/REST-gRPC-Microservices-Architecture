package com.thesis.bankingservice.client;


import brave.grpc.GrpcTracing;
import com.thesis.generated.PaymentResponse;
import com.thesis.generated.Practice;
import com.thesis.generated.PracticeResponse;
import com.thesis.generated.VerifyServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Profile;

@Profile("grpc")
public class MailSMSClientGRPC {
    private final ManagedChannel chan;

    public MailSMSClientGRPC(String host, GrpcTracing grpcTracing){
        this.chan= ManagedChannelBuilder.forTarget(host)
                .usePlaintext()
                .intercept(grpcTracing.newClientInterceptor())
                .build();
    }

    public Practice practiceOverview(Practice practice){
        VerifyServiceGrpc.VerifyServiceBlockingStub stub=VerifyServiceGrpc.newBlockingStub(chan);
        Practice response=stub.practiceOverview(practice);
        return response;
    }

}
