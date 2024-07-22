package com.app.dto.orderdetail;

import java.math.BigDecimal;

import com.app.dto.product.ProductResponse;

public record OrderDetailResponse(
                Long id,
                Long orderId,
                ProductResponse product,
                Integer quantity,
                BigDecimal subtotal) {

}
