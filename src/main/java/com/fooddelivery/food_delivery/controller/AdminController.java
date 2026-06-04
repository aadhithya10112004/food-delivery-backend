package com.fooddelivery.food_delivery.controller;

import com.fooddelivery.food_delivery.dto.response.DashboardResponse;
import com.fooddelivery.food_delivery.entity.Order;
import com.fooddelivery.food_delivery.entity.Role;
import com.fooddelivery.food_delivery.repository.OrderRepository;
import com.fooddelivery.food_delivery.repository.RestaurantRepository;
import com.fooddelivery.food_delivery.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    public AdminController(
            OrderRepository orderRepository,
            UserRepository userRepository,
            RestaurantRepository restaurantRepository
    ) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/dashboard")
    public DashboardResponse getDashboard() {

        Long totalOrders =
                orderRepository.count();

        Long totalCustomers =
                userRepository.findAll()
                        .stream()
                        .filter(user ->
                                user.getRole() == Role.CUSTOMER)
                        .count();

        Long totalRestaurants =
                restaurantRepository.count();

        Double totalRevenue =
                orderRepository.findAll()
                        .stream()
                        .filter(order -> order.getTotalAmount() != null)
                        .mapToDouble(Order::getTotalAmount)
                        .sum();

        return new DashboardResponse(
                totalOrders,
                totalCustomers,
                totalRestaurants,
                totalRevenue
        );
    }
}