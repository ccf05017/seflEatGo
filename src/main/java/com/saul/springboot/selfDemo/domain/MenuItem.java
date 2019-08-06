package com.saul.springboot.selfDemo.domain;

public class MenuItem {

    private Long restaurantId;
    private String menuName;

    public MenuItem(long restaurantId, String menuName) {
        this.restaurantId = restaurantId;
        this.menuName = menuName;
    }


    public String getMenuName() {
        return this.menuName;
    }

    public Long getRestaurantId() {
        return this.restaurantId;
    }
}
