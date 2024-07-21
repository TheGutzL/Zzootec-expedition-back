package com.app.dto.auth;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest(@NotBlank String username,
                @NotBlank String password,
                @NotBlank String firstName,
                @NotBlank String lastName,
                @NotBlank String email,
                @Valid AuthCreateRoleRequest roleRequest) {

}
