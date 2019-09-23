package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    RestaurantRepository restaurantRepository;

    ReviewRepository reviewRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<Restaurant> getRestaurants(String region, Long categoryId) {

//        restaurantFilterType = filterDTO.getRestaurantFilterType();
//
//        return restaurantFilterType.getRestaurantsByFilter(filterDTO.getRegion(), filterDTO.getCategoryId());

//        restaurantFilterType = filterDTO.getRestaurantFilterType();
//
//        List<Restaurant> restaurants
//                = restaurantFilterType.getRestaurantsByFilter(filterDTO.getRegion(), filterDTO.getCategoryId());

        List<Restaurant> restaurants
                = restaurantRepository.findAllByAddressContainingAndCategoryId(
                        region, categoryId);

        return restaurants;

    }

    public Restaurant getRestaurant(Long id) {

        Restaurant restaurant = restaurantRepository
                .findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

        List<Review> reviews = reviewRepository.findAllByRestaurantId(id);
        restaurant.setReviews(reviews);

        return restaurant;
    }
}