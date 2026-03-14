package com.nodevault.nodevaultbackend.service;

import com.nodevault.nodevaultbackend.dto.AuthResponse;
import com.nodevault.nodevaultbackend.dto.LoginRequest;
import com.nodevault.nodevaultbackend.dto.RegisterRequest;
import com.nodevault.nodevaultbackend.entity.User;
import com.nodevault.nodevaultbackend.repository.UserRepository;
import com.nodevault.nodevaultbackend.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repo;
    private final JwtUtil jwt;

    public AuthResponse register(RegisterRequest req) {

        repo.findByEmail(req.getEmail()).ifPresent(u -> {
            throw new RuntimeException("Email already registered");
        });

        User user = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(req.getPassword())
                .createdAt(LocalDateTime.now())
                .build();

        repo.save(user);

        String token = jwt.generateToken(user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .user(user)
                .build();
    }

    public AuthResponse login(LoginRequest req) {

        User user = repo.findByEmail(req.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(req.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwt.generateToken(user.getEmail());

        return AuthResponse.builder()
                .token(token)
                .user(user)
                .build();
    }
}