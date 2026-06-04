package com.fooddelivery.food_delivery.service.impl;

import com.fooddelivery.food_delivery.dto.request.OrderItemRequest;
import com.fooddelivery.food_delivery.dto.request.OrderRequest;
import com.fooddelivery.food_delivery.entity.Cart;
import com.fooddelivery.food_delivery.entity.MenuItem;
import com.fooddelivery.food_delivery.entity.Order;
import com.fooddelivery.food_delivery.entity.OrderItem;
import com.fooddelivery.food_delivery.entity.OrderStatus;
import com.fooddelivery.food_delivery.entity.User;
import com.fooddelivery.food_delivery.repository.CartRepository;
import com.fooddelivery.food_delivery.repository.MenuItemRepository;
import com.fooddelivery.food_delivery.repository.OrderItemRepository;
import com.fooddelivery.food_delivery.repository.OrderRepository;
import com.fooddelivery.food_delivery.repository.UserRepository;
import com.fooddelivery.food_delivery.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;

    public OrderServiceImpl(
            OrderRepository orderRepository,
            UserRepository userRepository,
            MenuItemRepository menuItemRepository,
            OrderItemRepository orderItemRepository,
            CartRepository cartRepository
    ) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.menuItemRepository = menuItemRepository;
        this.orderItemRepository = orderItemRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public Order placeOrder(OrderRequest request) {

        User user = userRepository.findById(
                request.getUserId()
        ).orElseThrow(
                () -> new RuntimeException("User not found")
        );

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);

        double totalAmount = 0.0;

        for (OrderItemRequest itemRequest : request.getItems()) {

            MenuItem menuItem = menuItemRepository
                    .findById(itemRequest.getMenuItemId())
                    .orElseThrow(
                            () -> new RuntimeException(
                                    "Menu Item not found"
                            )
                    );

            OrderItem orderItem = new OrderItem();

            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(itemRequest.getQuantity());
            orderItem.setItemPrice(menuItem.getPrice());
            orderItem.setOrder(order);

            order.getOrderItems().add(orderItem);

            totalAmount +=
                    menuItem.getPrice() *
                            itemRequest.getQuantity();
        }

        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        // CLEAR CART AFTER ORDER IS PLACED
        Cart cart = cartRepository
                .findByUserId(user.getId())
                .orElse(null);

        if (cart != null) {

            cart.getCartItems().clear();

            cartRepository.save(cart);
        }

        return savedOrder;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public Order updateOrderStatus(
            Long orderId,
            String status
    ) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Order not found"
                        )
                );

        order.setStatus(
                OrderStatus.valueOf(
                        status.toUpperCase()
                )
        );

        return orderRepository.save(order);
    }
}