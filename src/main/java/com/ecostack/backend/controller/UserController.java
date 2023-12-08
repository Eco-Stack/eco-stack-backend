package com.ecostack.backend.controller;

import com.ecostack.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/test")
    public ResponseEntity<Void> test() {return ResponseEntity.ok(null);}
}
