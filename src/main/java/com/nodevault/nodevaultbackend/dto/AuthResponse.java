package com.nodevault.nodevaultbackend.dto;

import com.nodevault.nodevaultbackend.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    private String token;
    private User user;
}