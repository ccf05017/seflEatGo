package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.Restaurant;

import java.util.List;

public class RestaurantFilterRegion implements RestaurantFilterType {

    @Override
    public List<Restaurant> getRestaurantsByFilter(String region, Long categoryId) {
        return null;
    }
}
