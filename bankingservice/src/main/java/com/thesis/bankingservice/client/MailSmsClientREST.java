package com.thesis.bankingservice.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.bankingservice.model.PracticeEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.reactive.function.client.WebClient;

public class MailSmsClientREST {
    private ObjectMapper obj = new ObjectMapper();
    private final Logger logger = LogManager.getLogger(MailSmsClientREST.class);
    private final String MailSmsServiceUrl;
    private final WebClient webClient;

    public MailSmsClientREST(String mailSmsServiceUrl, WebClient.Builder webClientBuilder) {
        this.MailSmsServiceUrl = mailSmsServiceUrl;
        this.webClient = webClientBuilder.baseUrl(MailSmsServiceUrl).build();
    }

    public PracticeEntity practiceOverview(PracticeEntity practice) {
        PracticeEntity result = webClient.post()
                .uri("/practice-overview")
                .bodyValue(practice)
                .retrieve()
                .bodyToMono(PracticeEntity.class)
                .block();
        return result;
    }

    ;
}
