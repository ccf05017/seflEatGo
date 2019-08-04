package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.domain.Restaurant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable Long id) {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(3333L, "Nandos", "Seoul"));
        restaurants.add(new Restaurant(4444L, "Manimal", "Itaewon"));
        restaurants.add(new Restaurant(5555L, "Bonasera", "Gangnam"));

        // Java Streams 활용 -> 공식 문서에서 사용법 꼭 숙지할 것
        Restaurant restaurant = restaurants.stream()
            .filter(restaurantObj -> restaurantObj.getId().equals(id))
            .findFirst()
            .orElse(null);

        return restaurant;
    }
}
