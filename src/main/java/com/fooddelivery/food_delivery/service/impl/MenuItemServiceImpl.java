package com.fooddelivery.food_delivery.service.impl;

import com.fooddelivery.food_delivery.dto.request.MenuItemRequest;
import com.fooddelivery.food_delivery.entity.MenuItem;
import com.fooddelivery.food_delivery.entity.Restaurant;
import com.fooddelivery.food_delivery.exception.ResourceNotFoundException;
import com.fooddelivery.food_delivery.repository.MenuItemRepository;
import com.fooddelivery.food_delivery.repository.RestaurantRepository;
import com.fooddelivery.food_delivery.service.MenuItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemServiceImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuItemServiceImpl(
            MenuItemRepository menuItemRepository,
            RestaurantRepository restaurantRepository
    ) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public MenuItem createMenuItem(MenuItemRequest request) {

        Restaurant restaurant = restaurantRepository
                .findById(request.getRestaurantId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Restaurant not found"
                        )
                );

        MenuItem menuItem = new MenuItem();

        menuItem.setName(request.getName());
        menuItem.setDescription(request.getDescription());
        menuItem.setPrice(request.getPrice());
        menuItem.setRestaurant(restaurant);

        return menuItemRepository.save(menuItem);
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    @Override
    public MenuItem getMenuItemById(Long id) {

        return menuItemRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Menu item not found"
                        )
                );
    }

    @Override
    public List<MenuItem> getMenuByRestaurant(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public MenuItem updateMenuItem(
            Long id,
            MenuItemRequest request
    ) {

        MenuItem menuItem = getMenuItemById(id);

        menuItem.setName(request.getName());
        menuItem.setDescription(request.getDescription());
        menuItem.setPrice(request.getPrice());

        return menuItemRepository.save(menuItem);
    }

    @Override
    public void deleteMenuItem(Long id) {

        MenuItem menuItem = getMenuItemById(id);

        menuItemRepository.delete(menuItem);
    }
}