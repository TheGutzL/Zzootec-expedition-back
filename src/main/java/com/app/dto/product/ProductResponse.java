package com.app.dto.product;

import com.app.dto.category.CategoryResponse;

public record ProductResponse(
        Long id,
        String name,
        String description,
        double price,
        Integer quantity,
        String image,
        CategoryResponse category) {

}
