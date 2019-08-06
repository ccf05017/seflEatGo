package com.saul.springboot.selfDemo.domain;

import org.springframework.beans.factory.annotation.Autowired;

public class Restaurant {

    private Long id;
    private final String name;
    private String address;
    private MenuItem menuItem;

    @Autowired
    private MenuItemRepository menuItemRepository;

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

    public MenuItem getMenuItem() {
        return this.menuItem;
    }

    public void addMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public void addMenuItemById(Long id) {
        this.addMenuItem(menuItem);
    }
}
