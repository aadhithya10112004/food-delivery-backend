package com.fooddelivery.food_delivery.dto.request;

import java.util.List;

public class PlaceOrderRequest {

    private Long userId;

    private List<OrderItemRequest> items;

    public PlaceOrderRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequest> items) {
        this.items = items;
    }
}