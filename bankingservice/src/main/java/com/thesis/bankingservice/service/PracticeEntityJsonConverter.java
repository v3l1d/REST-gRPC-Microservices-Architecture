package com.thesis.bankingservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thesis.bankingservice.model.PracticeEntity;

public class PracticeEntityJsonConverter {

    public static String practiceEntityToJson(PracticeEntity practiceEntity) {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode practiceNode = mapper.createObjectNode();
        practiceNode.put("practiceId", practiceEntity.getPracticeId());
        practiceNode.put("status", practiceEntity.getStatus());
        practiceNode.put("name", practiceEntity.getName());
        practiceNode.put("surname", practiceEntity.getSurname());
        practiceNode.put("email", practiceEntity.getEmail());
        practiceNode.put("phone", practiceEntity.getPhone());
        practiceNode.put("paymentMethod", practiceEntity.getPaymentMethod());
        try {
            // Parse JSON strings and add them to the JSON object
            practiceNode.set("additionalInfo", mapper.readTree(practiceEntity.getAdditionalInfo()));
            practiceNode.set("financingInfo", mapper.readTree(practiceEntity.getFinancingInfo()));
            practiceNode.set("vehicleInfo", mapper.readTree(practiceEntity.getVehicleInfo()));
            practiceNode.set("paymentInfo", mapper.readTree(practiceEntity.getPaymentInfo()));

            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(practiceNode);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
