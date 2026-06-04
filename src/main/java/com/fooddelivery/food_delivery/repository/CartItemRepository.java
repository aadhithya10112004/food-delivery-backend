package com.fooddelivery.food_delivery.repository;

import com.fooddelivery.food_delivery.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository
        extends JpaRepository<CartItem, Long> {
}