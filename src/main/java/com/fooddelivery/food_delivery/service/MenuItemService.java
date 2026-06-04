package com.fooddelivery.food_delivery.service;

import com.fooddelivery.food_delivery.dto.request.MenuItemRequest;
import com.fooddelivery.food_delivery.entity.MenuItem;

import java.util.List;

public interface MenuItemService {

    MenuItem createMenuItem(MenuItemRequest request);

    List<MenuItem> getAllMenuItems();

    MenuItem getMenuItemById(Long id);

    List<MenuItem> getMenuByRestaurant(Long restaurantId);

    MenuItem updateMenuItem(
            Long id,
            MenuItemRequest request
    );

    void deleteMenuItem(Long id);
}