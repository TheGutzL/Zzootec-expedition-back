package com.microservice.product.service;

import java.util.List;

import com.microservice.product.models.Product;

public interface IProductService {
    List<Product> findAll();

    Product findById(Long id);

    void save(Product product);

    List<Product> findByIdCategory(Long idCategory);
}
