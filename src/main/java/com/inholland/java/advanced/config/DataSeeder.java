package com.inholland.java.advanced.config;

import com.inholland.java.advanced.enums.Role;
import com.inholland.java.advanced.models.User;
import com.inholland.java.advanced.services.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements ApplicationRunner {
    private final UserService userService;

    public DataSeeder(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) {
        User admin = new User(
                1,
                "username",
                "password",
                List.of(Role.ROLE_ADMIN)
        );
        userService.createUser(admin);
    }
}
