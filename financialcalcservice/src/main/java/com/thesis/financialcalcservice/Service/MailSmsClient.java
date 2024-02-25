package com.thesis.financialcalcservice.Service;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import com.thesis.generated.Sms;
import com.thesis.generated.VerifyServiceGrpc;
import com.thesis.generated.Mail;
import com.thesis.generated.Otp;
import com.thesis.generated.Response;

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

    public String sendSmsOtp(String prefix, String number) {
        String result="";
        try {
            Sms request = Sms.newBuilder()
                .setPrefix(prefix)
                .setNumber(number)
                .build();
            Otp response = blockingStub.smsOtp(request);
            result=response.getPassword();
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        }
        return result;
    }

    public String sendMailOtp(String email){
            String result="";
            try{
                Mail mail= Mail.newBuilder()
                    .setAddress(email)
                    .build();
                Otp response=blockingStub.mailOtp(mail);
                result=response.getPassword();
            }catch (StatusRuntimeException e){
                System.err.println("RPC failed: " + e.getStatus());
            }

        return result;
    }

    public Boolean verifySms(String password){
        Boolean res=false;
        try{
            if((password.isEmpty()==false)){
                Otp toVerify=Otp.newBuilder()
                    .setPassword(password)
                    .build();
                Response resp=blockingStub.verifySmsOtp(toVerify);
                res=resp.getVerified();
            }
        }catch (StatusRuntimeException e){
            e.printStackTrace();
        }
        return res;
    }

    public Boolean verifyMail(String password){
        Boolean res=false;
            try{
                if(password.isEmpty()==false){
                    Otp toVerify=Otp.newBuilder()  
                        .setPassword(password)
                        .build();
                    Response resp=blockingStub.verifyMailOtp(toVerify);
                    res=resp.getVerified();

                }
            }catch (StatusRuntimeException e){
                e.printStackTrace();

            }


        return res;
    }

    
}

