package com.thesis.ratingservice.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.thesis.ratingservice.model.PracticeEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Profile("rest")
@RestController
public class RatingController {
    private final ObjectMapper obj = new ObjectMapper();
    private final Logger logger = LogManager.getLogger(RatingController.class);

    @PostMapping("/evaluate-practice")
    public ResponseEntity<PracticeEntity> evaluatePractice(@RequestBody PracticeEntity practice) {
        logger.info(practice);
        if (practice!=null) {
            double kpi1 = calculateKPI1(practice);
            double overallScore = (kpi1) / 3;
            String evaluation = evaluatePractice(overallScore);
            logger.info(practice);
            PracticeEntity response=practice;

            response.setEvaluationResult(evaluation);
          //  logger.info("KPI1: {}, KPI2: {}, KPI3: {}, Overall Score: {}", kpi1, kpi2, kpi3, overallScore);
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    private double calculateKPI1(PracticeEntity practice) {
        // Example KPI calculation
        double kpi1 = 200/ 12;
        return kpi1 > 10 ? 10 : kpi1; // KPI1 has a maximum score of 10
    }

    private double calculateKPI2(PracticeEntity practice) {
        // Example KPI calculation
        double kpi2 = practice.getFinancingInfo().getLoanAmount()/ 12;
        return kpi2 > 10 ? 10 : kpi2; // KPI2 has a maximum score of 10
    }

    private double calculateKPI3(PracticeEntity practice) {
        // Example KPI calculation
        double kpi3 = practice.getAdditionalInfo().getProvince().equalsIgnoreCase("NEW YORK")? 10 : 0;
        return kpi3; // KPI3 has a maximum score of 10
    }

    private String evaluatePractice(double overallScore) {
        if (overallScore >= 8) {
            return "GOOD PRACTICE!";
        } else if (overallScore >= 5) {
            return "AVERAGE PRACTICE!";
        } else {
            return "BELOW AVERAGE PRACTICE!";
        }
    }
}