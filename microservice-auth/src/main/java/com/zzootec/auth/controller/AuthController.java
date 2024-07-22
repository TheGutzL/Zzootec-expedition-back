package com.zzootec.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.zzootec.auth.dto.LoginRequest;

@RestController
public class AuthController {
    
    @PostMapping("/login")
    public ResponseEntity<String> login(
        @RequestBody LoginRequest loginRequest
    ) {
        System.out.println("Login request received");
        return ResponseEntity.ok().build();
    }


}
