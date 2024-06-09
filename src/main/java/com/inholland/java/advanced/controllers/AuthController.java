package com.inholland.java.advanced.controllers;

import com.inholland.java.advanced.requests.LoginRequest;
import com.inholland.java.advanced.responses.LoginResponse;
import com.inholland.java.advanced.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest)
            throws AuthenticationException {
        return new ResponseEntity<>(userService.login(loginRequest), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody LoginRequest loginRequest) {
        return  new ResponseEntity<>(userService.register(loginRequest), HttpStatus.CREATED);
    }
}