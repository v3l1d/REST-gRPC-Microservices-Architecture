package com.thesis.financialcalcservice.client;


import com.thesis.financialcalcservice.Service.FinancingService;
import com.thesis.financialcalcservice.Service.VehicleService;
import com.thesis.financialcalcservice.model.Financing;
import com.thesis.financialcalcservice.model.Vehicle;
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
   private final FinancingService financingService;
   private final VehicleService vehicleService;

    public BankingClientREST(String bankingServiceUrl, WebClient.Builder webClientBuilder, FinancingService financingService, VehicleService vehicleService){
        this.BankingServiceUrl=bankingServiceUrl;
        this.webClient=webClientBuilder.baseUrl(BankingServiceUrl).build();
        this.financingService = financingService;
        this.vehicleService = vehicleService;
    }
 public String createPractice(Customer personalData, String financingId,String reqId) {


        Financing temp= financingService.getFinancingById(financingId);
        Vehicle vehicleTemp=vehicleService.getVehicleById(temp.getVehicleId());
        ObjectNode requestBody = obj.createObjectNode()
            .putPOJO("financingInfo",temp)
            .putPOJO("vehicleInfo",vehicleTemp)
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
                
        logger.info("REQUEST ID:{} INPUT:{}{}{} OUTPUT:{}",reqId,personalData,temp.toString(),vehicleTemp.toString(),response );
        return response;
    } catch (WebClientException e) {
        logger.error("Error occurred during HTTP request: {}", e.getMessage());
        // Handle the exception or rethrow it
    }
    return financingId;
}

    
}
