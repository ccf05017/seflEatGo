package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.domain.MenuItem;
import com.saul.springboot.selfDemo.domain.MenuItemRepository;
import com.saul.springboot.selfDemo.domain.Restaurant;
import com.saul.springboot.selfDemo.domain.RestaurantRepository;
import com.saul.springboot.selfDemo.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list() {

        List<Restaurant> restaurants = this.restaurantRepository.findAll();

        for (Restaurant restaurant : restaurants) {
            List<MenuItem> menuItems = this.menuItemRepository.getMenuItemsById(restaurant.getId());
            restaurant.addMenuItems(menuItems);
        }

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable Long id) {

        // Service에서 restaurant, menuitem 모두 갖고 오도록 변경
        Restaurant restaurant = this.restaurantService.getRestaurantById(id);

        return restaurant;
    }
}
