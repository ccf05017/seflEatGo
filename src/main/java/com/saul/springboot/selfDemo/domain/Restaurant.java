package com.saul.springboot.selfDemo.domain;

import java.util.List;

public class Restaurant {

    private Long id;
    private final String name;
    private String address;
    private MenuItem menuItem;

    private List<MenuItem> menuItems;

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getInfo() {
        return this.name + " in " + this.address;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void addMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
