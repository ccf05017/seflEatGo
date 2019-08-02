package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.domain.Restaurant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RestaurantController {

    @GetMapping("/restaurants")
    public List<Restaurant> list() {
        List<Restaurant> restaurants = new ArrayList<>();

        // 빠와 하드 코딩으로 나오는 중 -> 수정 필요
        Restaurant restaurant = new Restaurant(3333L, "Nandos", "Seoul");
        restaurants.add(restaurant);

        return restaurants;
    }
}
