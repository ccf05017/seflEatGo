package com.saul.springboot.selfDemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String address;
    
    @Transient
    private List<ItemMenu> itemMenus = new ArrayList<>();

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

    public Long getId() {

        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {

        return this.name;
    }

    public String getAddress() {

        return this.address;
    }

    public String getInformation() {

        return this.name + " in " + this.address;
    }

    public List<ItemMenu> getItemMenus() {
        return itemMenus;
    }

    public void addItemMenu(ItemMenu itemMenu) {
        itemMenus.add(itemMenu);
    }

    public void setItemMenus(List<ItemMenu> itemMenus) {
        for (ItemMenu itemMenu : itemMenus) {
            addItemMenu(itemMenu);
        }
    }

    public void updateRestaurantInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }
}