package com.fooddelivery.food_delivery.service;

import com.fooddelivery.food_delivery.dto.request.AddToCartRequest;
import com.fooddelivery.food_delivery.entity.Cart;

public interface CartService {

    Cart addToCart(AddToCartRequest request);

    Cart getCartByUser(Long userId);

    void removeCartItem(Long cartItemId);
}