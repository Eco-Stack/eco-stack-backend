package com.ecostack.backend.controller;

import com.ecostack.backend.dto.user.UserDto;
import com.ecostack.backend.model.UserInfo;
import com.ecostack.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    private final UserService userService;

    @PostMapping("/v1/signup")
    public ResponseEntity<UserInfo> signUp(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.signUp(userDto));
    }

    @GetMapping("/test")
    public ResponseEntity<Void> test() {return ResponseEntity.ok(null);}
}
