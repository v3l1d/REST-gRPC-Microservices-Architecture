package com.thesis.collectorservice.client;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thesis.collectorservice.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import com.thesis.collectorservice.model.UserDataModels.UserData;
import reactor.core.publisher.Mono;

@Profile("rest")
public class BankingClientREST {
    private final Logger logger= LogManager.getLogger(BankingClientREST.class);
    private final WebClient webClient;
    private final ObjectMapper obj=new ObjectMapper();
    private final String BankingServiceUrl;


    public BankingClientREST(String bankingServiceUrl, WebClient.Builder webClientBuilder){
        this.BankingServiceUrl=bankingServiceUrl;
        this.webClient=webClientBuilder.baseUrl(bankingServiceUrl).build();
    }

    public boolean insertAdditionalInfo(String practiceId,AdditionalInfo additionalInfo){
        String result=webClient.post()
                .uri("/additional-info?practiceId="+practiceId)
                .bodyValue(additionalInfo)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        logger.info(result);
        if(result.equals("added")){
            return true;
        }else {

            return false;
        }
    }



    public boolean insertBtInfo(String practiceId, Transfer transfer){
        String result = webClient.post()
                .uri("/bank-transfer-payment?practiceId=" + practiceId)
                .bodyValue(transfer)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        logger.info(result);
        return result.equals("accepted");
    }


    public PracticeEntity sendToEvaluation(String practiceId, UserData userData) throws JsonProcessingException {
        PracticeEntity practice = new PracticeEntity();
        practice.setPracticeId(practiceId);
        practice.setUserData(userData);
        logger.info(practice);
        PracticeEntity practiceJson = webClient.post()
                .uri("/evaluate-practice?practiceId="+practiceId)
                .bodyValue(practice)
                .retrieve()
                .bodyToMono(PracticeEntity.class)
                .block();
        return practiceJson;
    }

    public boolean insertCardInfo(String practiceId, Card card) {
        String result = webClient.post()
                .uri("/credit-card-payment?practiceId=" + practiceId)
                .bodyValue(card)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        logger.info(result);
        return result.equals("accepted");
    }

    public boolean insertPersonalDocument(String practiceId, PersonalDocument personalDocument){
        String result=webClient.post()
                .uri("/personal-document?practiceId=" + practiceId)
                .bodyValue(personalDocument)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return result.equals("added");
    }

    public boolean insertCreditDocument(String practiceId, String creditDocument){
        String result=webClient.post()
                .uri("/credit-document?practiceId="+ practiceId)
                .bodyValue(creditDocument)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return result.equals("added");
    }

    public boolean practiceExists(String practiceId){
        Boolean resp=webClient.get()
                .uri("/practice-exists?practiceId="+practiceId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        return Boolean.TRUE.equals(resp);
    }

    public String practiceOverview(String practiceId){
        String result = webClient.get()
                .uri("/practice-overview?practiceId=" + practiceId)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        logger.info(result);
        return result;
    }

    public void sentToBank(UserData userData) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        String jsonString = objectMapper.writeValueAsString(userData);
        ObjectNode requestBody=objectMapper.createObjectNode()
                .put("userData",jsonString);
        String result=webClient.post()
                .uri("/get-user-data")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        logger.info(result);
    }
}
