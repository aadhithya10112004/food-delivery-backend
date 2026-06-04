package com.fooddelivery.food_delivery.controller;

import com.fooddelivery.food_delivery.dto.request.MenuItemRequest;
import com.fooddelivery.food_delivery.entity.MenuItem;
import com.fooddelivery.food_delivery.service.MenuItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuItemController {

    private final MenuItemService menuItemService;

    public MenuItemController(
            MenuItemService menuItemService
    ) {
        this.menuItemService = menuItemService;
    }

    @PostMapping
    public MenuItem createMenuItem(
            @RequestBody MenuItemRequest request
    ) {
        return menuItemService.createMenuItem(request);
    }

    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuItemService.getAllMenuItems();
    }

    @GetMapping("/{id}")
    public MenuItem getMenuItemById(
            @PathVariable Long id
    ) {
        return menuItemService.getMenuItemById(id);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<MenuItem> getMenuByRestaurant(
            @PathVariable Long restaurantId
    ) {
        return menuItemService.getMenuByRestaurant(restaurantId);
    }

    @PutMapping("/{id}")
    public MenuItem updateMenuItem(
            @PathVariable Long id,
            @RequestBody MenuItemRequest request
    ) {
        return menuItemService.updateMenuItem(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteMenuItem(
            @PathVariable Long id
    ) {
        menuItemService.deleteMenuItem(id);
        return "Menu Item deleted successfully";
    }
}