package com.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.category.CategoryRequest;
import com.app.dto.category.CategoryResponse;
import com.app.mapper.CategoryMapper;
import com.app.persistence.entity.CategoryEntity;
import com.app.persistence.repository.CategoryRepository;
import com.app.service.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse findById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow();
        return CategoryMapper.entityToDto(categoryEntity);
    }

    @Override
    public CategoryResponse save(CategoryRequest categoryRequest) {
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .name(categoryRequest.name())
                .description(categoryRequest.description())
                .build();

        CategoryEntity categorySaved = categoryRepository.save(categoryEntity);
        return CategoryMapper.entityToDto(categorySaved);
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest categoryRequest) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow();

        categoryEntity.setName(categoryRequest.name());
        categoryEntity.setDescription(categoryRequest.description());

        CategoryEntity updateEntity = categoryRepository.save(categoryEntity);
        return CategoryMapper.entityToDto(updateEntity);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

}
