package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.dto.product.ProductRequest;
import com.app.dto.product.ProductResponse;

public interface IProductService {

    List<ProductResponse> findAll();

    Page<ProductResponse> findAllPaginated(Pageable pageable);

    ProductResponse findById(Long id);

    ProductResponse save(ProductRequest product);

    ProductResponse update(Long id, ProductRequest product);

    void delete(Long id);

}
