package com.thesis.ratingservice.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Profile("rest")
@RestController
public class RatingController {
    private final ObjectMapper obj=new ObjectMapper();
    private final Logger logger=LogManager.getLogger(RatingController.class);
    @PostMapping("/evaluate-practice")
    public ResponseEntity<String> evaluatePractice(@RequestHeader(value="Request-ID")String reqId,@RequestBody String pract) throws JsonProcessingException {
        JsonNode practice=obj.readTree(pract);
        logger.info(pract);
        if(practice.has("practiceId")){
            return ResponseEntity.ok().body("GOOD PRACTICE!");
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}
