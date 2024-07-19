package com.app.mapper;

import com.app.dto.category.CategoryResponse;
import com.app.dto.product.ProductResponse;
import com.app.persistence.entity.ProductEntity;

public class ProductMapper {

    public static ProductResponse entityToDto(ProductEntity product) {
        CategoryResponse categoryResponse = CategoryMapper.entityToDto(product.getCategory());

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getImage(),
                categoryResponse);
    }

    public static ProductEntity dtoToEntity(ProductResponse productResponse) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productResponse.id());
        productEntity.setName(productResponse.name());
        productEntity.setDescription(productResponse.description());
        productEntity.setPrice(productResponse.price());
        productEntity.setQuantity(productResponse.quantity());
        productEntity.setImage(productResponse.image());
        productEntity.setCategory(CategoryMapper.dtoToEntity(productResponse.category()));
        return productEntity;
    }

}
