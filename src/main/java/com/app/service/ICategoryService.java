package com.app.service;

import java.util.List;

import com.app.dto.category.CategoryRequest;
import com.app.dto.category.CategoryResponse;

public interface ICategoryService {
    List<CategoryResponse> findAll();

    CategoryResponse findById(Long id);

    CategoryResponse save(CategoryRequest categoryRequest);

    CategoryResponse update(Long id, CategoryRequest categoryRequest);

    void delete(Long id);
}
