package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ItemMenuRepository itemMenuRepository;

    @Autowired
    ReviewRepository reviewRepository;

    public RestaurantService(RestaurantRepository restaurantRepository,
                             ItemMenuRepository itemMenuRepository,
                             ReviewRepository reviewRepository) {
        this.restaurantRepository = restaurantRepository;
        this.itemMenuRepository = itemMenuRepository;
        this.reviewRepository = reviewRepository;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        return restaurants;
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

    public Restaurant addRestaurant(Restaurant restaurant) {

        return restaurantRepository.save(restaurant);
    }

    // ??????? 뭔 말이디..?
    @Transactional
    public Restaurant updateRestaurant(long id, String name, String address) {

        Restaurant restaurant = restaurantRepository
                .findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));

        restaurant.updateRestaurantInfo(name, address);

        return restaurant;
    }
}