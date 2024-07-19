package com.app.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.dto.user.UserRequest;
import com.app.dto.user.UserResponse;
import com.app.persistence.entity.RoleEntity;
import com.app.persistence.entity.UserEntity;
import com.app.persistence.repository.RoleRepository;

@Component
public class UserMapper {

    private static RoleRepository roleRepository = null;

    public static UserResponse entityToDto(UserEntity userEntity) {
        return new UserResponse(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.isEnabled(),
                userEntity.isAccountNoExpired(),
                userEntity.isAccountNoLocked(),
                userEntity.isCredentialNoExpired(),
                userEntity.getRoles().stream().map(RoleMapper::entityToDto).collect(Collectors.toList()));
    }

    public static UserEntity dtoToEntity(UserRequest userRequest) {
        Set<RoleEntity> roles = new HashSet<>();

        if (userRequest.roleRequest() != null && userRequest.roleRequest().roleNames() != null) {
            List<String> roleNamesList = new ArrayList<>(userRequest.roleRequest().roleNames());
            for (String roleName : roleNamesList) {
                List<RoleEntity> foundRoles = roleRepository
                        .findRoleEntitiesByRoleEnumIn(Collections.singletonList(roleName));
                roles.addAll(foundRoles);
            }
        }

        return UserEntity.builder()
                .username(userRequest.username())
                .firstName(userRequest.firstName())
                .lastName(userRequest.lastName())
                .email(userRequest.email())
                .password(userRequest.password())
                .roles(roles)
                .build();

    }

    @Autowired
    public UserMapper(RoleRepository roleRepository) {
        UserMapper.roleRepository = roleRepository;
    }
}
