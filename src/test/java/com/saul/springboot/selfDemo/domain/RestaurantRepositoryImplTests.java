package com.saul.springboot.selfDemo.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
public class RestaurantRepositoryImplTests {

    @Test
    public void save() {
        RestaurantRepository restaurantRepository = new RestaurantRepositoryImpl();

        int beforeCount = restaurantRepository.findAll().size();

        Restaurant restaurant = new Restaurant("sushidama", "mokdong");
        Restaurant saved = restaurantRepository.save(restaurant);

        int afterCount = restaurantRepository.findAll().size();

        assertThat(saved.getId(), is(6666L));
        assertThat(afterCount-beforeCount, is(1));
    }
}
