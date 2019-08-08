package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.RestaurantService;
import com.saul.springboot.selfDemo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list() {

        List<Restaurant> restaurants = this.restaurantService.getRestaurants();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable Long id) {

        // Service에서 restaurant, menuitem 모두 갖고 오도록 변경
        Restaurant restaurant = this.restaurantService.getRestaurantById(id);

        return restaurant;
    }

    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@RequestBody Restaurant resource) throws URISyntaxException {

        String name = resource.getName();
        String address = resource.getAddress();
        Restaurant restaurant = new Restaurant(5555L, name, address);

        URI uri = new URI("/restaurants/" + restaurant.getId());
        this.restaurantService.addRestaurant(restaurant);

        return ResponseEntity.created(uri).body("{}");
    }
}
