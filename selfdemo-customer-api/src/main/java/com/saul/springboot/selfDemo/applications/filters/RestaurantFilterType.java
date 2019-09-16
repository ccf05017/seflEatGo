package com.saul.springboot.selfDemo.applications.filters;

import com.saul.springboot.selfDemo.domain.Restaurant;

import java.util.List;

public interface RestaurantFilterType {

    List<Restaurant> getRestaurantsByFilter(String region, Long categoryId);

}
