package com.saul.springboot.selfDemo.applications.filters;

import com.saul.springboot.selfDemo.domain.Restaurant;

import java.util.List;

public class RestaurantFilterBoth implements RestaurantFilterType {

    @Override
    public List<Restaurant> getRestaurantsByFilter(String region, Long categoryId) {
        return null;
    }
}
