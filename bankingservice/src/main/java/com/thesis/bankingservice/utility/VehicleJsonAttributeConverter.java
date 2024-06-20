package com.thesis.bankingservice.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thesis.bankingservice.model.Vehicle;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;

@Converter(autoApply = true)
public class VehicleJsonAttributeConverter implements AttributeConverter<Vehicle, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(vehicle);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error converting Vehicle to JSON string", e);
        }
    }

    @Override
    public Vehicle convertToEntityAttribute(String json) {
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            return objectMapper.readValue(json, Vehicle.class);
        } catch (IOException e) {
            throw new IllegalArgumentException("Error converting JSON string to Vehicle", e);
        }
    }
}
