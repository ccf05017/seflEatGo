package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.DTO.RestaurantSearchDto;
import com.saul.springboot.selfDemo.applications.RestaurantService;
import com.saul.springboot.selfDemo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list(
            RestaurantSearchDto searchDto
    ) {

        List<Restaurant> restaurants = restaurantService.getRestaurants(
                searchDto.getRegion(),
                searchDto.getCategoryId());

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {

        Restaurant restaurant = restaurantService.getRestaurant(id);

        return restaurant;
    }
}
