package com.thesis.bankingservice.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thesis.bankingservice.model.Card;
import com.thesis.bankingservice.model.Transfer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;


@Profile("rest")
public class PaymentClientREST {
    private final WebClient webClient;
    private final ObjectMapper obj=new ObjectMapper();
    private final String PaymentServiceUrl;
    private final Logger logger=LogManager.getLogger(PaymentClientREST.class);

    public PaymentClientREST(String paymentServiceUrl,WebClient.Builder webClientBuilder){
        this.PaymentServiceUrl=paymentServiceUrl;
        this.webClient=webClientBuilder.baseUrl(PaymentServiceUrl).build();

    }
    public boolean creditCardPayment(Card card){
        ObjectNode requestBody=obj.createObjectNode()
                .putPOJO("creditCard",card);
        String result=webClient.post()
                .uri("/credit-card")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return Objects.equals(result, "ACCEPTED");

    }
    public boolean bankTransferPayment(Transfer transfer){
        ObjectNode requestBody=obj.createObjectNode()
                .putPOJO("bankTransfer",transfer);
        String result=webClient.post()
                .uri("/bank-transfer")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return Objects.equals(result, "ACCEPTED");

    }



}
