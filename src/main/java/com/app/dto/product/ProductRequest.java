package com.app.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductRequest(
        @NotBlank(message = "no debe estar vacío") String name,
        @NotBlank(message = "no debe estar vacío") String description,
        @NotNull(message = "no debe ser nulo") @PositiveOrZero(message = "debe ser mayor o igual que 0") Double price,
        @NotNull(message = "no debe ser nulo") @Positive(message = "debe ser mayor que 0") Long categoryId,
        @NotNull(message = "no debe ser nulo") @PositiveOrZero(message = "debe ser mayor o igual que 0") Integer quantity,
        @NotBlank(message = "no debe estar vacío") String image) {

}
