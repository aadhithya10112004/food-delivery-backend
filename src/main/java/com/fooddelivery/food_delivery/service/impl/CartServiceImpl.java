package com.fooddelivery.food_delivery.service.impl;

import com.fooddelivery.food_delivery.dto.request.AddToCartRequest;
import com.fooddelivery.food_delivery.entity.*;
import com.fooddelivery.food_delivery.exception.ResourceNotFoundException;
import com.fooddelivery.food_delivery.repository.*;
import com.fooddelivery.food_delivery.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;

    public CartServiceImpl(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            UserRepository userRepository,
            MenuItemRepository menuItemRepository
    ) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public Cart addToCart(AddToCartRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        MenuItem menuItem = menuItemRepository.findById(
                request.getMenuItemId()
        ).orElseThrow(() ->
                new ResourceNotFoundException("Menu item not found"));

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setMenuItem(menuItem);
        cartItem.setQuantity(request.getQuantity());

        cart.getCartItems().add(cartItem);

        cartItemRepository.save(cartItem);

        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartByUser(Long userId) {

        return cartRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart not found"));
    }

    @Override
    public void removeCartItem(Long cartItemId) {

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cart Item not found"));

        cartItemRepository.delete(cartItem);
    }
}