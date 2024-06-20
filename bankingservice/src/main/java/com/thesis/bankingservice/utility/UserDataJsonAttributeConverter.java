package com.thesis.bankingservice.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.bankingservice.model.UserDataModels.model.UserData;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;

@Converter(autoApply = true)
public class UserDataJsonAttributeConverter implements AttributeConverter<UserData, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(UserData userData) {
        if (userData == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(userData);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting UserData to JSON string", e);
        }
    }

    @Override
    public UserData convertToEntityAttribute(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            return objectMapper.readValue(json, UserData.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON string to UserData", e);
        }
    }
}
