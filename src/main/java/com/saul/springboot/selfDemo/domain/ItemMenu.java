package com.saul.springboot.selfDemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ItemMenu {

    @Id
    @GeneratedValue
    private Long Id;

    private Long restaurantId;
    private String name;

    public ItemMenu(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
