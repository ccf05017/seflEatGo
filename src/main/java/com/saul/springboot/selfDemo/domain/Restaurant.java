package com.saul.springboot.selfDemo.domain;

import java.util.List;

public class Restaurant {

    private Long id;
    private String name;
    private String address;
    private MenuItem menuItem;

    private List<MenuItem> menuItems;

    public Restaurant() {
    }

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public void setId(long id) {
        this.id = id;
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
