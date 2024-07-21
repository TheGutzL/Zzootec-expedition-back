package com.app.service;

import java.util.List;

import com.app.dto.user.UserRequest;
import com.app.dto.user.UserResponse;

public interface IUserService {
    List<UserResponse> findAll();

    UserResponse findById(Long id);

    UserResponse getUserByUsername(String username);

    UserResponse save(UserRequest userRequest);

    UserResponse update(Long id, UserRequest userRequest);

    void delete(Long id);
}
