package com.fooddelivery.food_delivery.dto.response;

import com.fooddelivery.food_delivery.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private Long userId;
    private String name;
    private Role role;
}