package com.thesis.bankingservice.client;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thesis.bankingservice.model.PracticeEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.client.WebClient;

@Profile("rest")
public class RatingClientREST {
    private final WebClient webClient;
    private final ObjectMapper obj=new ObjectMapper();
    private final Logger logger=LogManager.getLogger(RatingClientREST.class);
    public RatingClientREST(String host,WebClient.Builder webClientBuilder){
        this.webClient=webClientBuilder.baseUrl(host).build();
    }

    public PracticeEntity getPracticeEvaluation(PracticeEntity practice){
        logger.info(practice);
        PracticeEntity response=webClient.post()
                .uri("/evaluate-practice")
                .bodyValue(practice)
                .retrieve()
                .bodyToMono(PracticeEntity.class)
                .block();
        return response;
    }

}
