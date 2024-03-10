package com.thesis.financialcalcservice.client;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thesis.financialcalcservice.model.Customer;

public class BankingClientREST {
    private final Logger logger=LogManager.getLogger(BankingClientREST.class);
    private final WebClient webClient=WebClient.builder().baseUrl("http://localhost:9090").build();
    private final ObjectMapper obj=new ObjectMapper();


    public BankingClientREST(){

    }
 public String createPractice(Customer personalData, String financingId) {
    ObjectNode requestBody = obj.createObjectNode()
            .put("financingId", financingId)
            .putPOJO("personalData", personalData);
    try {
        String response = webClient.post()
                .uri("/create-practice")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    } catch (WebClientException e) {
        logger.error("Error occurred during HTTP request: {}", e.getMessage());
        // Handle the exception or rethrow it
    }
    return financingId;
}

    
}
