package com.app.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProductQuantity(
        @NotNull(message = "El id del producto no puede ser nulo") Long productId,
        @NotNull(message = "La cantidad no puede ser nula") @Min(value = 1, message = "La cantidad debe ser mayor o igual a 1") Integer quantity) {
}
