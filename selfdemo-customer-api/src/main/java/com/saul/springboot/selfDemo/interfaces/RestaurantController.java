package com.saul.springboot.selfDemo.interfaces;

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
            @RequestParam(name = "region") String region,
            @RequestParam(name = "categoryId") Long categoryId
    ) {

        // TODO
        // region, categoryId 여부에 따라 서비스 선택할 수 있도록 분기 로직 추가?
        // 아니면 다른 단일 책임 원칙으로 변화?
        List<Restaurant> restaurants = restaurantService.getRestaurants(region, categoryId);

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable("id") Long id) {

        Restaurant restaurant = restaurantService.getRestaurant(id);

        return restaurant;
    }
}
