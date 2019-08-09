package com.saul.springboot.selfDemo.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
public class RestaurantRepositoryImplTests {

    @Test
    public void addRestaurant() {

        RestaurantRepository restaurantRepository = new RestaurantRepositoryImpl();

        int beforeSize = restaurantRepository.findAll().size();

        Restaurant restaurant = new Restaurant(5555L, "sushidama", "mokdong");
        restaurantRepository.addRestaurant(restaurant);

        int afterSize = restaurantRepository.findAll().size();

        assertThat(afterSize - beforeSize, is(1));
    }
}
