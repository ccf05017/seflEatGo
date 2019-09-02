package com.saul.springboot.selfDemo.domain;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException(Long id) {

        super("Restaurant not found: " + id);
    }

}
