package com.thesis.paymentservice.service;

import com.thesis.generated.BankPayment;
import com.thesis.generated.CardPayment;
import com.thesis.generated.PaymentGrpc;
import com.thesis.generated.PaymentResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.context.annotation.Profile;
import java.util.Random;

@Profile("grpc")
@GrpcService
public class PaymentServiceImpl extends PaymentGrpc.PaymentImplBase {

    @Override
    public void cardPay(CardPayment request,StreamObserver<PaymentResponse> responseObserver){

        PaymentResponse resp= PaymentResponse.newBuilder()
                .setPaymentId(generatePaymentId())
                .setResult("ACCEPTED")
                .build();
            responseObserver.onNext(resp);
            responseObserver.onCompleted();

    }

    @Override
    public void bankPay(BankPayment request,StreamObserver<PaymentResponse> responseObserver){
        PaymentResponse resp= PaymentResponse.newBuilder()
                .setPaymentId(generatePaymentId())
                .setResult("ACCEPTED")
                .build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }




    public String generatePaymentId(){
        // Generate a timestamp-based component
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        // Define the length of the ID
        int length = 5;

        // Create a StringBuilder to build the ID
        StringBuilder sb = new StringBuilder();

        // Create a random number generator
        Random random = new Random();

        // Generate a random character for each position in the ID
        for (int i = 0; i < length; i++) {
            // Get a random index from the characters string
            int randomIndex = random.nextInt(characters.length());

            // Append the random character to the ID
            sb.append(characters.charAt(randomIndex));
        }

        // Convert StringBuilder to a String and return the generated ID
        return sb.toString();
    }

}