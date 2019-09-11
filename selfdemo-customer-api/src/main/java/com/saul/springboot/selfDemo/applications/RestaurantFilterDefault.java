package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.DTO.RestaurantFilterDTO;
import com.saul.springboot.selfDemo.domain.Restaurant;

import java.util.List;

public class RestaurantFilterDefault implements RestaurantFilterType {

    @Override
    public List<Restaurant> getRestaurantsByFilter(RestaurantFilterDTO filterDTO) {
        return null;
    }
}
