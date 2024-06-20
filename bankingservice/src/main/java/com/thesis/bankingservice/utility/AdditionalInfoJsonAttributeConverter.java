package com.thesis.bankingservice.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.thesis.bankingservice.model.AdditionalInfo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;
import java.text.SimpleDateFormat;

@Converter(autoApply = true)
public class AdditionalInfoJsonAttributeConverter implements AttributeConverter<AdditionalInfo, String> {

    private ObjectMapper objectMapper = new ObjectMapper();
    public AdditionalInfoJsonAttributeConverter() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
    }
    @Override
    public String convertToDatabaseColumn(AdditionalInfo additionalInfo) {
        if (additionalInfo == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(additionalInfo);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting AdditionalInfo to JSON string", e);
        }
    }

    @Override
    public AdditionalInfo convertToEntityAttribute(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            return objectMapper.readValue(json, AdditionalInfo.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON string to AdditionalInfo", e);
        }
    }
}
