package com.app.dto.user;

import com.app.dto.role.RoleRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
                @NotBlank String username,
                @NotBlank String password,
                @NotBlank String email,
                @NotBlank String firstName,
                @NotBlank String lastName,
                @NotNull @Valid RoleRequest roleRequest) {

}
