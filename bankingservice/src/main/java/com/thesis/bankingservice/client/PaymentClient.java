package com.thesis.bankingservice.client;
import com.thesis.generated.PaymentGrpc;
import com.thesis.generated.PaymentRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PaymentClient{
    private final Logger logger=LogManager.getLogger(PaymentClient.class);
    private final ManagedChannel chan;
    private final PaymentGrpc.PaymentBlockingStub stub;


    public PaymentClient(String host, int port) {
        this.chan=ManagedChannelBuilder.forAddress(host,port)
                .usePlaintext()
                .build();
        this.stub= PaymentGrpc.newBlockingStub(chan);


    }

    public void paymentRequestCard(String method){
        try{
            if(method.equals("CREDIT-CARD")){
                PaymentRequest request=PaymentRequest.newBuilder()
                        .build();
            }
        }catch( Exception e) {
            logger.error("Request failed",e);
        }
    }

    public void paymentRequestBankTransfer(PaymentRequest request){

    }

}
