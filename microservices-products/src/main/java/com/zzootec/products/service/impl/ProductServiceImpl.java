package com.zzootec.products.service.impl;

import com.zzootec.products.dto.ProductPurchaseRequest;
import com.zzootec.products.dto.ProductPurchaseResponse;
import com.zzootec.products.dto.ProductRequest;
import com.zzootec.products.dto.ProductResponse;
import com.zzootec.products.exception.ProductPurchaseException;
import com.zzootec.products.mapper.ProductMapper;
import com.zzootec.products.models.Product;
import com.zzootec.products.repository.ProductRepository;
import com.zzootec.products.service.IProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = ProductPurchaseException.class)
    public List<ProductPurchaseResponse> purchaseProducts(
            List<ProductPurchaseRequest> productPurchaseRequests
    ) {
        List<Long> productIds = productPurchaseRequests
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        List<Product> storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }
        List<ProductPurchaseRequest> sortedRequest = productPurchaseRequests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        List<ProductPurchaseResponse> purchaseProducts = new ArrayList<ProductPurchaseResponse>();
        for (int i = 0; i < storedProducts.size(); i++) {
            Product product = storedProducts.get(i);
            ProductPurchaseRequest productPurchaseRequest = sortedRequest.get(i);
            if (product.getAvailableQuantity() < productPurchaseRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID::" + productPurchaseRequest.productId());
            }
            double newAvailableQuantity = product.getAvailableQuantity() - productPurchaseRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchaseProducts.add(productMapper.toProductPurchaseResponse(product, productPurchaseRequest.quantity()));
        }
        return purchaseProducts;
    }

    @Override
    public ProductResponse findById(Long id) {
        return productRepository.findById(id)
                .map(productMapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + id));
    }

    @Override
    public Long createProduct(ProductRequest productRequest) {
        Product product = productMapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }
}
