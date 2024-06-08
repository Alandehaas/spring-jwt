package com.inholland.java.advanced.requests;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
