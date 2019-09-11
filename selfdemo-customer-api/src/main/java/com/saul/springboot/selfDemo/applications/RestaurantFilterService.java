package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.DTO.RestaurantFilterDTO;
import com.saul.springboot.selfDemo.domain.Restaurant;
import com.saul.springboot.selfDemo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RestaurantFilterService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public List<Restaurant> getRestaurants(RestaurantFilterDTO filterDTO) {

        String region = filterDTO.getRegion();
        Long categoryId = filterDTO.getCategoryId();

        List<Restaurant> restaurants = restaurantRepository.findAll();

        if (region != null) {
            restaurants = restaurantRepository.findAllByAddressContaining(region);
        }

        if (categoryId != null) {
            restaurants = restaurantRepository.findAllByCategoryId(categoryId);
        }

        if (region != null & categoryId != null) {
            restaurants = restaurantRepository.findAllByAddressContainingAndCategoryId(region, categoryId);
        }

        return restaurants;
    }
}
