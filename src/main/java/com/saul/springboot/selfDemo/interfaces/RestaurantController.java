package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.domain.Restaurant;
import com.saul.springboot.selfDemo.domain.RestaurantRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantRepositoryImpl restaurantRepository;

    @GetMapping("/restaurants")
    public List<Restaurant> list() {

        List<Restaurant> restaurants = this.restaurantRepository.findAll();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable Long id) {

        Restaurant restaurant = this.restaurantRepository.findById(id);

        return restaurant;
    }
}
