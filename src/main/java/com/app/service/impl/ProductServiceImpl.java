package com.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.product.ProductRequest;
import com.app.dto.product.ProductResponse;
import com.app.mapper.ProductMapper;
import com.app.persistence.entity.CategoryEntity;
import com.app.persistence.entity.ProductEntity;
import com.app.persistence.repository.CategoryRepository;
import com.app.persistence.repository.ProductRepository;
import com.app.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream()
                .map(ProductMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse findById(Long id) {
        ProductEntity product = productRepository.findById(id).orElseThrow();
        return ProductMapper.entityToDto(product);
    }

    @Override
    public ProductResponse save(ProductRequest product) {
        CategoryEntity category = categoryRepository.findById(product.categoryId()).orElseThrow();

        ProductEntity productEntity = ProductEntity.builder()
                .name(product.name())
                .description(product.description())
                .price(product.price())
                .quantity(product.quantity())
                .image(product.image())
                .category(category)
                .build();

        ProductEntity productSaved = productRepository.save(productEntity);
        return ProductMapper.entityToDto(productSaved);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest product) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow();
        CategoryEntity category = categoryRepository.findById(product.categoryId()).orElseThrow();

        productEntity.setName(product.name());
        productEntity.setDescription(product.description());
        productEntity.setPrice(product.price());
        productEntity.setQuantity(product.quantity());
        productEntity.setImage(product.image());
        productEntity.setCategory(category);

        ProductEntity updateEntity = productRepository.save(productEntity);
        return ProductMapper.entityToDto(updateEntity);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
