package com.app.service;

import java.util.List;

import com.app.dto.product.ProductRequest;
import com.app.dto.product.ProductResponse;

public interface IProductService {

    List<ProductResponse> findAll();

    ProductResponse findById(Long id);

    ProductResponse save(ProductRequest product);

    ProductResponse update(Long id, ProductRequest product);

    void delete(Long id);

}
