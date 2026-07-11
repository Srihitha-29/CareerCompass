package com.srihitha.careercompass.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srihitha.careercompass.dto.LoginRequest;
import com.srihitha.careercompass.dto.LoginResponse;
import com.srihitha.careercompass.dto.RegisterRequest;
import com.srihitha.careercompass.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
public String registerUser(@Valid @RequestBody RegisterRequest request) {

    userService.registerUser(request);

    return "User Registered Successfully";
}
@PostMapping("/login")
public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
    LoginResponse response = userService.loginUser(request);
    return ResponseEntity.ok(response);
}
@GetMapping("/test")
public ResponseEntity<String> test() {
    return ResponseEntity.ok("JWT is Working!");
}
}
