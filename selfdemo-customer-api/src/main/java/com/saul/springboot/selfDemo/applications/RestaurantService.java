package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ItemMenuRepository itemMenuRepository;

    @Autowired
    ReviewRepository reviewRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        return restaurants;
    }

    public List<Restaurant> getRestaurants(String region) {

        return restaurantRepository.findAllByAddressContaining(region);
    }

    public Restaurant getRestaurant(Long id) {

        Restaurant restaurant = restaurantRepository
                .findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

        List<ItemMenu> itemMenus = itemMenuRepository.findAllByRestaurantId(id);
        restaurant.setItemMenus(itemMenus);

        List<Review> reviews = reviewRepository.findAllByRestaurantId(id);
        restaurant.setReviews(reviews);

        return restaurant;
    }
}