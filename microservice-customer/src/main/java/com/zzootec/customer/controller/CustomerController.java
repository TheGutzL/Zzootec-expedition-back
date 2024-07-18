package com.zzootec.customer.controller;

import com.zzootec.customer.dto.CustomerRequest;
import com.zzootec.customer.dto.CustomerResponse;
import com.zzootec.customer.service.impl.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll() {
        return ResponseEntity.ok(this.customerService.findAllCustomers());
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> findById(
            @PathVariable("customer-id") Long customerId
    ) {
        return ResponseEntity.ok(this.customerService.findById(customerId));
    }

    @GetMapping("/exists/{customer-id}")
    public ResponseEntity<Boolean> existsById(
            @PathVariable("customer-id") Long customerId
    ) {
        return ResponseEntity.ok(this.customerService.existsById(customerId));
    }

    @PostMapping
    public ResponseEntity<Long> createCustomer(
            @RequestBody @Valid CustomerRequest customerRequest
    ) {
        return ResponseEntity.ok(this.customerService.createCustomer(customerRequest));
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest customerRequest
    ) {
        this.customerService.updateCustomer(customerRequest);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable("customer-id") Long customerId
    ) {
        this.customerService.deleteCustomer(customerId);
        return ResponseEntity.accepted().build();
    }

}
