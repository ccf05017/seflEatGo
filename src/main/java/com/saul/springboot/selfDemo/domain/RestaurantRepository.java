package com.saul.springboot.selfDemo.domain;

import java.util.List;

public interface RestaurantRepository {

    public List<Restaurant> findAll();

    public Restaurant findById(Long id);
}
