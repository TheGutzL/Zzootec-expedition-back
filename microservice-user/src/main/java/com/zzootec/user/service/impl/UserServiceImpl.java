package com.zzootec.user.service.impl;

import com.zzootec.user.model.User;
import com.zzootec.user.repository.UserRepository;
import com.zzootec.user.service.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User addUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("El usuario ya existe");
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(Long idUser, User user) {
        if (!userRepository.existsById(idUser)) {
            throw new IllegalArgumentException("El usuario no existe");
        }
        // Asegúrate de que el ID del usuario proporcionado sea el mismo que el del usuario existente
        user.setId(idUser);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
