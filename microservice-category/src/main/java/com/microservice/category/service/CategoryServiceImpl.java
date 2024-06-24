package com.microservice.category.service;

import com.microservice.category.client.ProductClient;
import com.microservice.category.dto.ProductDTO;
import com.microservice.category.http.response.ProductByCategoryResponse;
import com.microservice.category.models.Category;
import com.microservice.category.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService{

    @Autowired
    private ICategoryRepository categoryRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public ProductByCategoryResponse findProductsByIdCategory(Long idCategory) {

        Category category = categoryRepository.findById(idCategory).orElse(new Category());

        List<ProductDTO> productDTOList = productClient.findAllProductByCategory(idCategory);

        return ProductByCategoryResponse.builder()
                .categoryName(category.getName())
                .productDTOList(productDTOList)
                .build();
    }
}
