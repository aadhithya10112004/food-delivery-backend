package com.fooddelivery.food_delivery.repository;

import com.fooddelivery.food_delivery.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository
        extends JpaRepository<OrderItem, Long> {
}