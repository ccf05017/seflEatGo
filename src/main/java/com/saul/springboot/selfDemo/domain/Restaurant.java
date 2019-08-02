package com.saul.springboot.selfDemo.domain;

public class Restaurant {

    private Long id;
    private final String name;
    private String address;

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getInfo() {
        return this.name + " in " + this.address;
    }
}
