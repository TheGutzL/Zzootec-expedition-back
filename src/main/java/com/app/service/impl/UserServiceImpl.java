package com.app.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.user.UserRequest;
import com.app.dto.user.UserResponse;
import com.app.mapper.UserMapper;
import com.app.persistence.entity.RoleEntity;
import com.app.persistence.entity.UserEntity;
import com.app.persistence.repository.RoleRepository;
import com.app.persistence.repository.UserRepository;
import com.app.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow();
        return UserMapper.entityToDto(userEntity);
    }

    @Override
    public UserResponse save(UserRequest userRequest) {
        List<String> roleNames = new ArrayList<>(userRequest.roleRequest().roleNames());
        Set<RoleEntity> roles = new HashSet<>(roleRepository.findRoleEntitiesByRoleEnumIn(roleNames));

        UserEntity userEntity = UserEntity.builder()
                .username(userRequest.username())
                .firstName(userRequest.firstName())
                .lastName(userRequest.lastName())
                .email(userRequest.email())
                .password(passwordEncoder.encode(userRequest.password()))
                .roles(roles)
                .isEnabled(true)
                .accountNoLocked(true)
                .accountNoExpired(true)
                .credentialNoExpired(true)
                .build();

        UserEntity savedUser = userRepository.save(userEntity);
        return UserMapper.entityToDto(savedUser);
    }

    @Override
    public UserResponse update(Long id, UserRequest userRequest) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con el ID: " + id));

        user.setUsername(userRequest.username());
        user.setEmail(userRequest.email());
        user.setFirstName(userRequest.firstName());
        user.setLastName(userRequest.lastName());
        user.setPassword(passwordEncoder.encode(userRequest.password()));

        List<String> roleNamesList = new ArrayList<>(userRequest.roleRequest().roleNames());
        Set<RoleEntity> roles = new HashSet<>(roleRepository.findRoleEntitiesByRoleEnumIn(roleNamesList));
        user.setRoles(roles);

        UserEntity updatedUser = userRepository.save(user);
        return UserMapper.entityToDto(updatedUser);
    }

    @Override
    public void delete(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + id));

        user.getRoles().clear();
        
        userRepository.save(user);

        userRepository.deleteById(id);
    }

}
