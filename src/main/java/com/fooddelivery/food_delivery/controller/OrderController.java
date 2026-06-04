package com.fooddelivery.food_delivery.controller;

import com.fooddelivery.food_delivery.dto.request.OrderRequest;
import com.fooddelivery.food_delivery.entity.Order;
import com.fooddelivery.food_delivery.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(
            OrderService orderService
    ) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order placeOrder(
            @RequestBody OrderRequest request
    ) {
        return orderService.placeOrder(request);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/user/{userId}")
    public List<Order> getOrdersByUser(
            @PathVariable Long userId
    ) {
        return orderService.getOrdersByUser(userId);
    }

    @PutMapping("/{orderId}/{status}")
    public Order updateStatus(
            @PathVariable Long orderId,
            @PathVariable String status
    ) {
        return orderService.updateOrderStatus(
                orderId,
                status
        );
    }
}