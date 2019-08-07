package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.MenuItem;
import com.saul.springboot.selfDemo.domain.MenuItemRepository;
import com.saul.springboot.selfDemo.domain.Restaurant;
import com.saul.springboot.selfDemo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    MenuItemRepository menuItemRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository) {

        this.restaurantRepository = restaurantRepository;
        this.menuItemRepository = menuItemRepository;
    }

    public List<Restaurant> getRestaurants() {

        List<Restaurant> restaurants = this.restaurantRepository.findAll();

        for (Restaurant restaurant : restaurants) {
            List<MenuItem> menuItems = this.menuItemRepository.getMenuItemsById(restaurant.getId());
            restaurant.addMenuItems(menuItems);
        }

        return restaurants;
    }

    public Restaurant getRestaurantById(Long id) {

        Restaurant restaurant = this.restaurantRepository.findById(id);

        List<MenuItem> menuItems = this.menuItemRepository.getMenuItemsById(id);
        restaurant.addMenuItems(menuItems);

        return restaurant;
    }
}
