package com.thesis.financialcalcservice.client;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thesis.financialcalcservice.model.Customer;

@Profile("rest")
public class BankingClientREST {
    private final Logger logger=LogManager.getLogger(BankingClientREST.class);
    private final WebClient webClient;
    private final ObjectMapper obj=new ObjectMapper();
   private final String BankingServiceUrl;

    public BankingClientREST(String bankingServiceUrl){
        this.BankingServiceUrl=bankingServiceUrl;
        this.webClient=WebClient.builder().baseUrl(BankingServiceUrl).build();

    }
 public String createPractice(Customer personalData, String financingId,double amount,String reqId) {
    ObjectNode requestBody = obj.createObjectNode()
            .put("financingId", financingId)
            .put("amount",amount)
            .putPOJO("personalData", personalData);
    try {
        HttpHeaders header= new HttpHeaders();
        header.add("Request-ID", reqId);
        String response = webClient.post()
                .uri("/create-practice")
                .header("Request-ID", reqId)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
                
        logger.info("REQUEST ID:{} INPUT:{}{} OUTPUT:{}",reqId,personalData,financingId,response );
        return response;
    } catch (WebClientException e) {
        logger.error("Error occurred during HTTP request: {}", e.getMessage());
        // Handle the exception or rethrow it
    }
    return financingId;
}

    
}
