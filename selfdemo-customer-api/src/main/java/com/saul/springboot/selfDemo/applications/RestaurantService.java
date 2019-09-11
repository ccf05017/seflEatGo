package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.DTO.RestaurantFilterDTO;
import com.saul.springboot.selfDemo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    RestaurantFilterType restaurantFilterType;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getRestaurants(RestaurantFilterDTO filterDTO) {

        restaurantFilterType = filterDTO.getRestaurantFilterType();

        return restaurantFilterType.getRestaurantsByFilter(filterDTO);
    }

    public Restaurant getRestaurant(Long id) {

        Restaurant restaurant = restaurantRepository
                .findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

        return restaurant;
    }
}