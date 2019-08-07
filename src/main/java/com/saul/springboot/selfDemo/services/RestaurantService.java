package com.saul.springboot.selfDemo.services;

import com.saul.springboot.selfDemo.domain.Restaurant;
import com.saul.springboot.selfDemo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {

        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant getRestaurantById(Long id) {

        Restaurant restaurant = this.restaurantRepository.findById(id);
        return restaurant;
    }
}
