package com.zzootec.products.controller;

import com.zzootec.products.dto.ProductPurchaseRequest;
import com.zzootec.products.dto.ProductPurchaseResponse;
import com.zzootec.products.dto.ProductRequest;
import com.zzootec.products.dto.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.zzootec.products.service.IProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findById(
            @PathVariable("product-id") Long productId
    ) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @PostMapping
    public ResponseEntity<Long> createProduct(
            @RequestBody @Valid ProductRequest request
    ) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProduct(
            @RequestBody List<ProductPurchaseRequest> request
    ) {
        return ResponseEntity.ok(productService.purchaseProducts(request));
    }


}
