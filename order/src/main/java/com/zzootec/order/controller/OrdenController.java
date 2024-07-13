package com.zzootec.order.controller;

import com.zzootec.order.dto.OrderRequest;
import com.zzootec.order.dto.OrderResponse;
import com.zzootec.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdenController {

    private final OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(
            @PathVariable("order-id") Long orderId
    ) {
        return ResponseEntity.ok(service.findById(orderId));
    }

    @PostMapping
    public ResponseEntity<Long> createOrder(
            @RequestBody @Valid OrderRequest request
    ) {
        return ResponseEntity.ok(service.createdOrder(request));
    }

}
