package com.thesis.financialcalcservice.client;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import com.thesis.generated.Sms;
import com.thesis.generated.VerifyServiceGrpc;
import com.thesis.generated.Mail;
import com.thesis.generated.Otp;
import com.thesis.generated.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;

@Profile("grpc")
public class MailSmsClientGRPC {
    private final ManagedChannel channel;
    private final VerifyServiceGrpc.VerifyServiceBlockingStub blockingStub;
    private static final Logger logger= LogManager.getLogger(MailSmsClientGRPC.class);
    public MailSmsClientGRPC(String host) {
        this.channel = ManagedChannelBuilder.forTarget(host)
                .usePlaintext()
                .build();
        this.blockingStub = VerifyServiceGrpc.newBlockingStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, java.util.concurrent.TimeUnit.SECONDS);
    }

    public String createSmsOtp(String number,String reqId) {
        String result="";

        try {
            Metadata headers = new Metadata();
            // Add the Request-ID header to the Metadata object
            Metadata.Key<String> requestIdKey = Metadata.Key.of("Request-ID", Metadata.ASCII_STRING_MARSHALLER);
            headers.put(requestIdKey, reqId);
            VerifyServiceGrpc.VerifyServiceBlockingStub stub= VerifyServiceGrpc.newBlockingStub(channel);
            // Attach the Metadata object to the stub
            stub=stub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(headers));
                  
            Sms request = Sms.newBuilder()
                .setNumber(number)
                .build();
            Otp response = blockingStub.smsOtp(request);
            result=response.getPassword();
            logger.info("REQUEST ID:{} INPUT:{} OUTPUT:{}",reqId,number,response.getPassword());
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
        }
        return result;
    }

    public String createMailOtp(String email,String reqId){
            String result="";
            try{
                Metadata headers = new Metadata();
        // Add the Request-ID header to the Metadata object
                Metadata.Key<String> requestIdKey = Metadata.Key.of("Request-ID", Metadata.ASCII_STRING_MARSHALLER);
                headers.put(requestIdKey, reqId);
                VerifyServiceGrpc.VerifyServiceBlockingStub stub= VerifyServiceGrpc.newBlockingStub(channel);
        // Attach the Metadata object to the stub
                stub=stub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(headers));
                Mail mail= Mail.newBuilder()
                    .setAddress(email)
                    .build();
                Otp response=stub.mailOtp(mail);
                result=response.getPassword();
                logger.info("REQUEST ID:{} INPUT:{} OUTPUT:{}",reqId,email,response.getPassword());
            }catch (StatusRuntimeException e){
                System.err.println("RPC failed: " + e.getStatus());
            }

        return result;
    }

    public Boolean verifySms(String password,String reqId){
        boolean res=false;
        try{
            if((!password.isEmpty())){
                Metadata headers = new Metadata();
                Metadata.Key<String> requestIdKey = Metadata.Key.of("Request-ID", Metadata.ASCII_STRING_MARSHALLER);
                headers.put(requestIdKey, reqId);
                VerifyServiceGrpc.VerifyServiceBlockingStub stub= VerifyServiceGrpc.newBlockingStub(channel);
                Otp toVerify=Otp.newBuilder()
                    .setPassword(password)
                    .build();
                Response resp=stub.verifySmsOtp(toVerify);
                res=resp.getVerified();
                
                logger.info("REQUEST ID:{} INPUT:{} OUTPUT:{}",reqId,password,res);
            }
        }catch (StatusRuntimeException e){
            logger.error("Error processing password",e);
        }
        return res;
    }

    public Boolean verifyMail(String password,String reqId){
        boolean res=false;
            try{
                if(!password.isEmpty()){
                    Metadata headers = new Metadata();
                    Metadata.Key<String> requestIdKey = Metadata.Key.of("Request-ID", Metadata.ASCII_STRING_MARSHALLER);
                    headers.put(requestIdKey, reqId);
                    VerifyServiceGrpc.VerifyServiceBlockingStub stub= VerifyServiceGrpc.newBlockingStub(channel);    
                    Otp toVerify=Otp.newBuilder()  
                        .setPassword(password)
                        .build();
                    Response resp=stub.verifyMailOtp(toVerify);
                    res=resp.getVerified();
                    logger.info("REQUEST ID:{} INPUT:{} OUTPUT:{}",reqId,password,res);

                }
            }catch (StatusRuntimeException e){
                logger.error("Error processing passowrd",e);

            }


        return res;
    }

    
}

