package com.fooddelivery.food_delivery.controller;

import com.fooddelivery.food_delivery.dto.request.LoginRequest;
import com.fooddelivery.food_delivery.dto.request.RegisterRequest;
import com.fooddelivery.food_delivery.dto.response.LoginResponse;
import com.fooddelivery.food_delivery.entity.User;
import com.fooddelivery.food_delivery.service.AuthService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(
            @Valid @RequestBody RegisterRequest request
    ) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(
            @Valid @RequestBody LoginRequest request
    ) {
        return authService.login(request);
    }
}