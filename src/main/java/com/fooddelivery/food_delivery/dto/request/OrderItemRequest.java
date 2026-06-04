package com.fooddelivery.food_delivery.dto.request;

public class OrderItemRequest {

    private Long menuItemId;
    private Integer quantity;

    public OrderItemRequest() {
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}