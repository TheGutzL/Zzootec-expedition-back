package com.app.dto.role;
import java.util.Set;

public record RoleResponse(
        Long id,
        String roleName,
        Set<String> permissions
) {

}
