package com.inholland.java.advanced.controllers;

import com.inholland.java.advanced.requests.LoginRequest;
import com.inholland.java.advanced.responses.LoginResponse;
import com.inholland.java.advanced.services.UserService;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) throws AuthenticationException {
        return userService.login(loginRequest);
    }

    @PostMapping("/register")
    public LoginResponse register(@RequestBody LoginRequest loginRequest) {
        return userService.register(loginRequest);
    }
}