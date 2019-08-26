package com.saul.springboot.selfDemo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String address;
    
    @Transient
    private List<ItemMenu> itemMenus = new ArrayList<>();

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getInformation() {

        return this.name + " in " + this.address;
    }

    public void addItemMenu(ItemMenu itemMenu) {
        itemMenus.add(itemMenu);
    }

    public void addItemMenus(List<ItemMenu> itemMenus) {
        for (ItemMenu itemMenu : itemMenus) {
            addItemMenu(itemMenu);
        }
    }

    public void updateRestaurantInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }
}