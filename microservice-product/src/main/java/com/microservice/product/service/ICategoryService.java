package com.microservice.product.service;

import java.util.List;

import com.microservice.product.models.Category;

public interface ICategoryService {
    List<Category> findAll();

    Category findById(Long id);

    void save(Category category);

    void deleteById(Long id);
}
