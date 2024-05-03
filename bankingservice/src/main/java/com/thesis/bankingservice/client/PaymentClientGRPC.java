package com.thesis.bankingservice.client;
import brave.grpc.GrpcTracing;
import com.thesis.bankingservice.model.Card;
import com.thesis.bankingservice.model.Transfer;
import com.thesis.generated.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.MetadataUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;


@Profile("grpc")
public class PaymentClientGRPC {
    private final Logger logger=LogManager.getLogger(PaymentClientGRPC.class);
    private final ManagedChannel chan;
   

    public PaymentClientGRPC(String host, GrpcTracing grpcTracing) {

         this.chan = ManagedChannelBuilder.forTarget(host)
                .usePlaintext()
                 .intercept(grpcTracing.newClientInterceptor())
                .build();


    }

    public String paymentRequestCard(Card card,String reqId) {
        String result="";
        try{
            logger.info("CARD:{}",card.toString());
        if (card != null) {
             Metadata headers = new Metadata();
                Metadata.Key<String> requestIdKey = Metadata.Key.of("Request-ID", Metadata.ASCII_STRING_MARSHALLER);
                headers.put(requestIdKey, reqId);
                logger.info("HEADER:{}",headers);
                PaymentGrpc.PaymentBlockingStub stub= PaymentGrpc.newBlockingStub(chan);
                stub=stub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(headers));

            CardPayment request = CardPayment.newBuilder()
                    .setCard(CreditCard.newBuilder().setCode(card.getCode()).setNumber(card.getCardNumber()).setOwner(card.getOwner()))
                    .build();
            PaymentResponse response = stub.cardPay(request);
            if(response.getResult().equals("ACCEPTED")){
                result="PAYMENT ACCEPTED with id:" + response.getPaymentId();
                logger.info("REQUEST ID:{} INPUT:{} OUTPUT:{}",reqId,card,response.getResult());
            }
        }}catch (StatusRuntimeException e){
            logger.error("PAYMENT REFUSED",e);
        }
        return result;
    }

    public String paymentRequestBank(Transfer transfer,String reqId){
        String result="";
        try{
            if(transfer!=null){
                Metadata headers = new Metadata();
                Metadata.Key<String> requestIdKey = Metadata.Key.of("Request-ID", Metadata.ASCII_STRING_MARSHALLER);
                headers.put(requestIdKey, reqId);
                logger.info("HEADER:{}",headers);
                PaymentGrpc.PaymentBlockingStub stub= PaymentGrpc.newBlockingStub(chan);
                stub=stub.withInterceptors(MetadataUtils.newAttachHeadersInterceptor(headers));
                BankPayment request= BankPayment.newBuilder()
                        .setTransfer(BankTransfer.newBuilder().setBankId(transfer.getBankId()).setOwner(transfer.getOwner()))
                        .build();

                PaymentResponse response=stub.bankPay(request);
                if(response.getResult().equals("ACCEPTED")){
                    logger.info("REQUEST ID:{} INPUT:{} OUTPUT:{}",reqId,transfer,response.getResult());
                    result=response.getPaymentId();
                }
            }
        }catch (StatusRuntimeException e){
            logger.error("PAYMENT REFUSED!",e);
        }
        return result;
    }





}
