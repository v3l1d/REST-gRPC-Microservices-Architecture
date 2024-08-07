package com.thesis.financialcalcservice.client;


import io.grpc.*;
import io.grpc.stub.MetadataUtils;
import com.thesis.generated.Sms;
import com.thesis.generated.VerifyServiceGrpc;
import com.thesis.generated.Mail;
import com.thesis.generated.Otp;
import com.thesis.generated.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import brave.grpc.GrpcTracing;

@Profile("grpc")
public class MailSmsClientGRPC {
    private final ManagedChannel channel;
    private final VerifyServiceGrpc.VerifyServiceBlockingStub blockingStub;
    private static final Logger logger= LogManager.getLogger(MailSmsClientGRPC.class);

    public MailSmsClientGRPC(String host, GrpcTracing grpcTracing) {
        this.channel = ManagedChannelBuilder.forTarget(host)
                .usePlaintext()
                .intercept(grpcTracing.newClientInterceptor())
                .build();
        this.blockingStub = VerifyServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
    }

    public String createSmsOtp(String number) {
        String result="";

        try {
            Sms request = Sms.newBuilder()
                .setNumber(number)
                .build();
            Otp response = blockingStub.smsOtp(request);
            result=response.getPassword();
            logger.info("INPUT:{} OUTPUT:{}",number,response.getPassword());
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        }
        return result;
    }

    public String createMailOtp(String email){
            String result="";
            try{
                Mail mail= Mail.newBuilder()
                    .setAddress(email)
                    .build();
                Otp response=blockingStub.mailOtp(mail);
                result=response.getPassword();
                logger.info("INPUT:{} OUTPUT:{}",email,response.getPassword());
            }catch (StatusRuntimeException e){
                System.err.println("RPC failed: " + e.getStatus());
            }

        return result;
    }

    public Boolean verifySms(String password){
        boolean res=false;
        try{
            if((!password.isEmpty())){
                Otp toVerify=Otp.newBuilder()
                    .setPassword(password)
                    .build();
                Response resp=blockingStub.verifySmsOtp(toVerify);
                res=resp.getVerified();
                
                logger.info("INPUT:{} OUTPUT:{}",password,res);
            }
        }catch (StatusRuntimeException e){
            logger.error("Error processing password",e);
        }
        return res;
    }

    public Boolean verifyMail(String password){
        boolean res=false;
            try{
                if(!password.isEmpty()){
                    Otp toVerify=Otp.newBuilder()  
                        .setPassword(password)
                        .build();
                    Response resp=blockingStub.verifyMailOtp(toVerify);
                    res=resp.getVerified();
                    logger.info("INPUT:{} OUTPUT:{}",password,res);

                }
            }catch (StatusRuntimeException e){
                logger.error("Error processing passowrd",e);

            }


        return res;
    }

    
}

