package com.thesis.mailsmsservice.Service;

import java.security.SecureRandom;
import java.util.Base64;

import com.thesis.generated.Mail;
import com.thesis.generated.Otp;
import com.thesis.generated.Sms;
import com.thesis.generated.Response;
import com.thesis.generated.VerifyServiceGrpc.VerifyServiceImplBase;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class VerifyServiceImpl extends VerifyServiceImplBase {
    private String otp;

    public  VerifyServiceImpl(){

    }
    @Override
    public void smsOtp(Sms verify, StreamObserver<Otp> responseObserver){
        if (verify.getPrefix().isEmpty() || verify.getNumber().isEmpty()) {
            // Throw an exception indicating that the fields are empty
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Prefix or number is empty").asException());
            return; // Stop further execution
        }
        try{
        String genOtp=generateRandomString(5);
       Otp password= Otp.newBuilder()
        .setPassword(genOtp)
        .build();

        responseObserver.onNext(password);
        responseObserver.onCompleted();
        this.otp=genOtp;
        System.out.println(this.otp);
        }catch (Exception e){
            responseObserver.onError(Status.INTERNAL.withDescription("Error generating OTP").asException());
        }
    }

    @Override
    public void mailOtp(Mail verify, StreamObserver<Otp> responseObserver){
        if(verify.getAddress().isEmpty()){
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("Email field is empty, unable to process the verification").asException());
            return ;
        }
        try{
            String genOtp=generateRandomString(5);
            Otp password=Otp.newBuilder()
                .setPassword(genOtp)
                .build();
            
            responseObserver.onNext(password);
            responseObserver.onCompleted();
        } catch (Exception e){
            responseObserver.onError(Status.INVALID_ARGUMENT.withDescription("ERROR generating OTP").asException());
        }
    }

    @Override
    public void  verifyOtp(Otp pass, StreamObserver<Response> respObserver){
        Boolean result;
        System.out.println(pass.getPassword());
        if(pass.getPassword().equals(this.otp)){
           result=true;
        }else{
            result=false;
        }

        Response resp=Response.newBuilder()
            .setVerified(result)
            .build();
        respObserver.onNext(resp);
        respObserver.onCompleted();
        

    }
    
    

      public static String generateRandomString(int length) {

        SecureRandom random = new SecureRandom();

        byte[] randomBytes = new byte[length];
        
        random.nextBytes(randomBytes);
        
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);

    }

    
}
