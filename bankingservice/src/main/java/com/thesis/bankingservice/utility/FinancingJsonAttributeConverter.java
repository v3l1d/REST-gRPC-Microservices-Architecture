package com.thesis.bankingservice.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.bankingservice.model.Financing;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;

@Converter(autoApply = true)
public class FinancingJsonAttributeConverter implements AttributeConverter<Financing, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Financing financing) {
        if (financing == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(financing);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting Financing to JSON string", e);
        }
    }

    @Override
    public Financing convertToEntityAttribute(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            return objectMapper.readValue(json, Financing.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON string to Financing", e);
        }
    }
}
