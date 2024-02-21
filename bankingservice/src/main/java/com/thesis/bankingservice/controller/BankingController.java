package com.thesis.bankingservice.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class BankingController {
    @GetMapping("/banking")
    public String test(@RequestBody String param) {
        return "Param: " + param;
    }
}