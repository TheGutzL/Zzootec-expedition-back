package com.app.mapper;

import com.app.dto.category.CategoryResponse;
import com.app.persistence.entity.CategoryEntity;

public class CategoryMapper {

    public static CategoryResponse entityToDto(CategoryEntity categoryEntity) {
        return new CategoryResponse(
                categoryEntity.getId(),
                categoryEntity.getName(),
                categoryEntity.getDescription());
    }

    public static CategoryEntity dtoToEntity(CategoryResponse categoryResponse) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryResponse.id());
        categoryEntity.setName(categoryResponse.name());
        categoryEntity.setDescription(categoryResponse.description());
        return categoryEntity;
    }

}
