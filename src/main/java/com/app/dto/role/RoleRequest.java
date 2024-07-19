package com.app.dto.role;

import java.util.Set;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RoleRequest(@NotNull @NotEmpty Set<String> roleNames
// @NotNull @NotEmpty Set<Long> permissionIds
) {

}
