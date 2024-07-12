package com.zzootec.products.service;

import com.zzootec.products.dto.ProductPurchaseRequest;
import com.zzootec.products.dto.ProductPurchaseResponse;
import com.zzootec.products.dto.ProductRequest;
import com.zzootec.products.dto.ProductResponse;

import java.util.List;

public interface IProductService {
    List<ProductResponse> findAll();
    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> productPurchaseRequests);
    ProductResponse findById(Long id);
    Long createProduct(ProductRequest productRequest);

}
