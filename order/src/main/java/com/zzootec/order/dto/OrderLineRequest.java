package com.zzootec.order.dto;

public record OrderLineRequest(
        Long id,
        Long orderId,
        Long productId,
        double quantity
) {
}
