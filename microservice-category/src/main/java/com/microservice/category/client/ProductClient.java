package com.microservice.category.client;

import com.microservice.category.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-product", url = "localhost:8080/api/product")
public interface ProductClient {
    @GetMapping("/search-by-category/{idCategory}")
    List<ProductDTO> findAllProductByCategory(@PathVariable Long idCategory);
}
