package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.ItemMenu;
import com.saul.springboot.selfDemo.domain.ItemMenuRepository;
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
    ItemMenuRepository itemMenuRepository;

    public RestaurantService(RestaurantRepository restaurantRepository,
                             ItemMenuRepository itemMenuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.itemMenuRepository = itemMenuRepository;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        return restaurants;
    }

    public Restaurant getRestaurant(Long id) {
        // 실무에서 이렇게 처리하면 안됨
        // 이 상황에서의 exception은 다음 강의에
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);

        List<ItemMenu> itemMenus = itemMenuRepository.findAllByRestaurantId(id);
        restaurant.setItemMenus(itemMenus);

        return restaurant;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }
}