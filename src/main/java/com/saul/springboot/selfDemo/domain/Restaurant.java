package com.saul.springboot.selfDemo.domain;

public class Restaurant {

    private final String name;
    private String address;

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
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
