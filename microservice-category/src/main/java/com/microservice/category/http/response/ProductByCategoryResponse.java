package com.microservice.category.http.response;

import com.microservice.category.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductByCategoryResponse {

    private String categoryName;
    private List<ProductDTO> productDTOList;
}
