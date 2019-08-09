package com.saul.springboot.selfDemo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantRepositoryImpl() {
        this.restaurants.add(new Restaurant(3333L, "Nandos", "Seoul"));
        this.restaurants.add(new Restaurant(4444L, "Manimal", "Itaewon"));
        this.restaurants.add(new Restaurant(5555L, "Bonasera", "Gangnam"));
    }

    public List<Restaurant> findAll() {

        return this.restaurants;
    }

    public Restaurant findById(Long id) {

        Restaurant restaurant = this.restaurants.stream()
                .filter(restaurantObject -> restaurantObject.getId().equals(id))
                .findFirst()
                .orElse(null);

        return restaurant;
    }

    @Override
    public void addRestaurant(Restaurant restaurant) {
        this.restaurants.add(restaurant);
    }

    @Override
    public void deleteRestaurant(Long id) {
        List<Restaurant> restaurants = this.restaurants.stream()
                .filter(restaurant -> !restaurant.getId().equals(id))
                .collect(Collectors.toList());

        this.restaurants = restaurants;
    }
}
