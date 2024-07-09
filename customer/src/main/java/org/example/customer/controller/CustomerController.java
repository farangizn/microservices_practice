package org.example.customer.controller;

import lombok.RequiredArgsConstructor;
import org.example.customer.dto.CustomerDTO;
import org.example.customer.service.CustomerService;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public HttpEntity<?> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerService.register(customerDTO);
    }
}
