package com.inholland.java.advanced.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public String getDemo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String firstRole = auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse("No roles");
        return "Hello " +
                auth.getName() +
                ", with a role of " +
                firstRole +
                ". You are authorized to access this endpoint.";
    }
}
