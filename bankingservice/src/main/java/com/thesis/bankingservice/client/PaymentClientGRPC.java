package com.thesis.bankingservice.client;
import com.thesis.bankingservice.model.Card;
import com.thesis.bankingservice.model.Transfer;
import com.thesis.generated.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.thesis.bankingservice.service.BankDBService;
public class PaymentClientGRPC {
    private final Logger logger=LogManager.getLogger(PaymentClientGRPC.class);
    private final PaymentGrpc.PaymentBlockingStub stub;

    private  BankDBService dbService;


    public PaymentClientGRPC(String host, int port) {

        ManagedChannel chan = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        this.stub= PaymentGrpc.newBlockingStub(chan);


    }

    public String paymentRequestCard(Card card) {
        String result="";
        try{
            logger.info("CARD:{}",card.toString());
        if (card != null) {
            CardPayment request = CardPayment.newBuilder()
                    .setCard(CreditCard.newBuilder().setCode(card.getCode()).setNumber(card.getCardNumber()).setOwner(card.getOwner()))
                    .build();
            PaymentResponse response = stub.cardPay(request);
            if(response.getResult().equals("ACCEPTED")){
                result="PAYMENT ACCEPTED with id:" + response.getPaymentId();
            }
        }}catch (StatusRuntimeException e){
            logger.error("PAYMENT REFUSED",e);
        }
        return result;
    }

    public String paymentRequestBank(Transfer transfer){
        String result="";
        try{
            if(transfer!=null){
                BankPayment request= BankPayment.newBuilder()
                        .setTransfer(BankTransfer.newBuilder().setBankId(transfer.getBankId()).setOwner(transfer.getOwner()))
                        .build();
                PaymentResponse response=stub.bankPay(request);
                if(response.getResult().equals("ACCEPTED")){
                    result=response.getPaymentId();
                }
            }
        }catch (StatusRuntimeException e){
            logger.error("PAYMENT REFUSED!",e);
        }
        return result;
    }





}
