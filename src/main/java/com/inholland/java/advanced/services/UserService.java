package com.inholland.java.advanced.services;

import com.inholland.java.advanced.enums.Role;
import com.inholland.java.advanced.jwt.JwtProvider;
import com.inholland.java.advanced.models.User;
import com.inholland.java.advanced.repositories.UserRepository;
import com.inholland.java.advanced.requests.LoginRequest;
import com.inholland.java.advanced.responses.LoginResponse;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public User createUser(User user) {
        if (userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            throw new BadCredentialsException("Username is already taken");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest loginRequest) throws AuthenticationException {

        User user = userRepository.findUserByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("User not found"));

        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid username/password");
        }

        return new LoginResponse(jwtProvider.createToken(user.getUsername(), user.getRoles()));
    }

    public LoginResponse register(LoginRequest loginRequest) {
        User savedUser = createUser(userMapper(loginRequest));
        return new LoginResponse(jwtProvider.createToken(savedUser.getUsername(), savedUser.getRoles()));
    }

    private User userMapper(LoginRequest loginRequest) {
        User user = new User();
        user.setUsername(loginRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(loginRequest.getPassword()));
        user.setRoles(List.of(Role.ROLE_USER));
        return user;
    }
}
