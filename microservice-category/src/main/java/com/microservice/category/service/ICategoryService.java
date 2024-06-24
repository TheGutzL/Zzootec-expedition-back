package com.microservice.category.service;

import com.microservice.category.http.response.ProductByCategoryResponse;
import com.microservice.category.models.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category findById(Long id);
    void save(Category category);
    ProductByCategoryResponse findProductsByIdCategory(Long idCategory);
}
