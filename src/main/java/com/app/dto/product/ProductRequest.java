package com.app.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductRequest(@NotBlank String name, @NotBlank String description, @PositiveOrZero double price,
        @Positive long categoryId, @PositiveOrZero int quantity, @NotBlank String image) {

}
