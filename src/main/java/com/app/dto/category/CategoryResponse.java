package com.app.dto.category;

import com.app.persistence.entity.CategoryEntity;

public record CategoryResponse(
        long id,
        String name,
        String description) {

    private CategoryResponse entityToDto(CategoryEntity categoryEntity) {
        return new CategoryResponse(
                categoryEntity.getId(),
                categoryEntity.getName(),
                categoryEntity.getDescription()
        );
    }
}
