package com.zzootec.user.controller;

import com.zzootec.user.model.User;
import com.zzootec.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("add")
    public ResponseEntity<?> signup(@RequestBody User user) {
        return ResponseEntity.ok(userService.addUser(user));
    }

    @PutMapping("update/{idUser}")
    public ResponseEntity<?> updateUser(@PathVariable Long idUser, @RequestBody User user) {
        userService.updateUser(idUser, user);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("delete/{idUser}")
    public ResponseEntity<?> deleteUser(@PathVariable Long idUser) {
        userService.deleteUser(idUser);
        return ResponseEntity.ok("User deleted Successfully");
    }
}
