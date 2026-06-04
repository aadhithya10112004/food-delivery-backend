package com.fooddelivery.food_delivery.service;

import com.fooddelivery.food_delivery.dto.request.OrderRequest;
import com.fooddelivery.food_delivery.entity.Order;

import java.util.List;

public interface OrderService {

    Order placeOrder(OrderRequest request);

    List<Order> getAllOrders();

    List<Order> getOrdersByUser(Long userId);

    Order updateOrderStatus(Long orderId, String status);
}