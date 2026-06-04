package com.fooddelivery.food_delivery.controller;

import com.fooddelivery.food_delivery.dto.request.AddToCartRequest;
import com.fooddelivery.food_delivery.entity.Cart;
import com.fooddelivery.food_delivery.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(
            CartService cartService
    ) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public Cart addToCart(
            @RequestBody AddToCartRequest request
    ) {
        return cartService.addToCart(request);
    }

    @GetMapping("/user/{userId}")
    public Cart getCart(
            @PathVariable Long userId
    ) {
        return cartService.getCartByUser(userId);
    }

    @DeleteMapping("/item/{cartItemId}")
    public String removeItem(
            @PathVariable Long cartItemId
    ) {
        cartService.removeCartItem(cartItemId);
        return "Item removed from cart";
    }
}