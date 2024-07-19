package com.app.dto.user;

import java.util.List;

import com.app.dto.role.RoleResponse;

public record UserResponse(
        Long id,
        String username,
        String email,
        String firstName,
        String lastName,
        boolean isEnabled,
        boolean accountNoExpired,
        boolean accountNoLocked,
        boolean credentialNoExpired,
        List<RoleResponse> roles) {

}
