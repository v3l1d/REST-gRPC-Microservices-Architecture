package com.thesis.financialcalcservice.Service;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import com.thesis.generated.Sms;
import com.thesis.generated.VerifyServiceGrpc;
import com.thesis.generated.Otp;

public class MailSmsClient {
    private final ManagedChannel channel;
    private final VerifyServiceGrpc.VerifyServiceBlockingStub blockingStub;

    public MailSmsClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        this.blockingStub = VerifyServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
    }

    public void sendSmsOtp(String prefix, String number) {
        try {
            Sms request = Sms.newBuilder()
                .setPrefix(prefix)
                .setNumber(number)
                .build();
            Otp response = blockingStub.smsOtp(request);
            System.out.println("Generated OTP: " + response.getPassword());
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        }
    }
    
}

