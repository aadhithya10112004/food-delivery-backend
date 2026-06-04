package com.fooddelivery.food_delivery.controller;

import com.fooddelivery.food_delivery.entity.Restaurant;
import com.fooddelivery.food_delivery.service.RestaurantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(
            RestaurantService restaurantService
    ) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public Restaurant addRestaurant(
            @RequestBody Restaurant restaurant
    ) {
        return restaurantService.addRestaurant(restaurant);
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(
            @PathVariable Long id
    ) {

        return restaurantService.getRestaurantById(id);
    }

    @PutMapping("/{id}")
    public Restaurant updateRestaurant(
            @PathVariable Long id,
            @RequestBody Restaurant restaurant
    ) {
        return restaurantService.updateRestaurant(
                id,
                restaurant
        );
    }

    @DeleteMapping("/{id}")
    public String deleteRestaurant(
            @PathVariable Long id
    ) {
        restaurantService.deleteRestaurant(id);
        return "Restaurant deleted successfully";
    }
}