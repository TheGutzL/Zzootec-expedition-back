package com.app.mapper;

import com.app.dto.role.RoleRequest;
import com.app.dto.role.RoleResponse;
import com.app.persistence.entity.PermissionEntity;
import com.app.persistence.entity.RoleEntity;
import com.app.persistence.entity.RoleEnum;

import java.util.Set;
import java.util.stream.Collectors;

public class RoleMapper {


    public static RoleResponse entityToDto(RoleEntity roleEntity) {
        return new RoleResponse(
                roleEntity.getId(),
                roleEntity.getRoleEnum().name(),
                roleEntity.getPermissionList().stream()
                        .map(PermissionEntity::getName)
                        .collect(Collectors.toSet())
        );
    }

    public static Set<RoleEntity> dtoToEntity(RoleRequest roleRequest) {
        return roleRequest.roleNames().stream().map(roleName -> {
            RoleEntity roleEntity = new RoleEntity();
            roleEntity.setRoleEnum(Enum.valueOf(RoleEnum.class, roleName));
            // roleEntity.setPermissionList(roleRequest.permissionIds().stream()
            //         .map(permissionId -> {
            //             PermissionEntity permission = new PermissionEntity();
            //             permission.setId(permissionId);
            //             return permission;
            //         }).collect(Collectors.toSet()));
            return roleEntity;
        }).collect(Collectors.toSet());
    }

}
