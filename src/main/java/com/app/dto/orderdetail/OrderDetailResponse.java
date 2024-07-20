package com.app.dto.orderdetail;

import java.math.BigDecimal;

public record OrderDetailResponse(
        Long id,
        Long orderId,
        Long productId,
        Integer quantity,
        BigDecimal subtotal) {

}
