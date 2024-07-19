package com.app.dto.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressRequest(
        @NotBlank(message = "La calle no puede ser nula ni vacia") @Size(max = 255, message = "La longitud de la calle no puede superar los 255 caracteres") String street,
        @NotBlank(message = "La ciudad no puede estar vacía") @Size(max = 100, message = "La longitud de la ciudad no puede superar los 100 caracteres") String city,
        @NotBlank(message = "El estado no puede estar vacío") @Size(max = 100, message = "La longitud del estado no puede superar los 100 caracteres") String state,
        @NotBlank(message = "El país no puede estar vacío") @Size(max = 100, message = "La longitud del país no puede superar los 100 caracteres") String country,
        @NotBlank(message = "El código postal no puede estar vacío") @Pattern(regexp = "^[0-9]{5}$", message = "El código postal debe tener 5 dígitos") String zipCode,
        @NotNull(message = "El id del usuario no puede ser nulo") Long userId) {

}
