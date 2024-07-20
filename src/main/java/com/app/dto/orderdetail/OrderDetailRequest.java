package com.app.dto.orderdetail;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderDetailRequest(
        @NotNull(message = "orderId no puede ser nulo") Long orderId,
        @NotNull(message = "productId no puede ser nulo") Long productId,
        @Positive(message = "La cantidad debe ser mayor que 0") Integer quantity) {

}
