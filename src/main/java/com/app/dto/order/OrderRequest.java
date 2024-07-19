package com.app.dto.order;

import java.math.BigDecimal;
import java.util.List;

import com.app.persistence.entity.OrderStatus;
import com.app.validation.EnumValidator;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record OrderRequest(
        @NotNull(message = "El id del usuario no puede ser nulo") Long userId,
        @NotNull(message = "El id de la direccion no puede ser nulo") Long shippingAddressId,
        @NotNull(message = "La lista de productos no puede ser nula") @Size(min = 1, message = "Debe haber al menos un producto en el pedido") List<ProductQuantity> products,
        @NotNull(message = "El total del pedido no puede ser nulo") BigDecimal total,
        @NotNull(message = "El estado del pedido no puede ser nulo") @EnumValidator(enumClass = OrderStatus.class, message = "El estado del pedido es invalido") String status) {
}
