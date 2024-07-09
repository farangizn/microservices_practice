package org.example.fraud.controller;

import lombok.RequiredArgsConstructor;
import org.example.clients.fraud.FraudCheckResponse;
import org.example.fraud.service.FraudCheckService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fraud-check")
public class FraudCheckController {

    private final FraudCheckService fraudCheckService;

    @GetMapping("{customerId}")
    public FraudCheckResponse checkFraudster(@PathVariable Integer customerId) {
        Boolean isFraudster = fraudCheckService.isFraudster(customerId);
        return new FraudCheckResponse(isFraudster);
    }
}
