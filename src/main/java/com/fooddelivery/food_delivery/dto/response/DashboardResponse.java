package com.fooddelivery.food_delivery.dto.response;

public class DashboardResponse {

    private Long totalOrders;
    private Long totalCustomers;
    private Long totalRestaurants;
    private Double totalRevenue;

    public DashboardResponse() {
    }

    public DashboardResponse(
            Long totalOrders,
            Long totalCustomers,
            Long totalRestaurants,
            Double totalRevenue
    ) {
        this.totalOrders = totalOrders;
        this.totalCustomers = totalCustomers;
        this.totalRestaurants = totalRestaurants;
        this.totalRevenue = totalRevenue;
    }

    public Long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public Long getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(Long totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public Long getTotalRestaurants() {
        return totalRestaurants;
    }

    public void setTotalRestaurants(Long totalRestaurants) {
        this.totalRestaurants = totalRestaurants;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}