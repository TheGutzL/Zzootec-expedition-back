package com.zzootec.user.service;

import com.zzootec.user.model.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();
    User getUserById(Long id);
    User addUser(User user);
    void updateUser(Long id, User user);
    void deleteUser(Long id);

}
