package com.ecostack.backend.controller;

import com.ecostack.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    @PostMapping("/v1/signup")
    public ResponseEntity<Void> signUp() {
        return null;
    }

    @PostMapping("/v1/login")
    public ResponseEntity<Void> login() {
        return null;
    }
}
