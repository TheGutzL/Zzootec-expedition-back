package com.app.dto.order;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.app.dto.address.AddressResponse;
import com.app.persistence.entity.OrderStatus;

public record OrderResponse(
                Long orderId,
                BigDecimal total,
                OrderStatus status,
                UserOrderResponse user,
                AddressResponse shippingAddress,
                LocalDateTime createdAt) {

}
