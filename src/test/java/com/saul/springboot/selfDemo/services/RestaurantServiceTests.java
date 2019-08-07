package com.saul.springboot.selfDemo.services;

import com.saul.springboot.selfDemo.domain.Restaurant;
import com.saul.springboot.selfDemo.domain.RestaurantRepository;
import com.saul.springboot.selfDemo.domain.RestaurantRepositoryImpl;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RestaurantServiceTests {

    RestaurantRepository restaurantRepository = new RestaurantRepositoryImpl();
    RestaurantService restaurantService = new RestaurantService(restaurantRepository);

    @Test
    public void getRestaurantById() {

        Restaurant restaurant = this.restaurantService.getRestaurantById(3333L);

        // restaurant info 확인
        assertThat(restaurant.getId(), is(3333L));
        assertThat(restaurant.getAddress(), is("Seoul"));
        assertThat(restaurant.getName(), is("Nandos"));
        assertThat(restaurant.getInfo(), is("Nandos in Seoul"));

        // menuitem info 확인
    }
}
