package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.Restaurant;
import com.saul.springboot.selfDemo.domain.RestaurantNotFoundException;
import com.saul.springboot.selfDemo.domain.RestaurantRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

public class RestaurantServiceTests {

    // 얘만으로는 DI 테스트를 할 수 없다
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    // Test 실행하기 전에 미리 수행되는 동작
    // DI를 직접 해주도록 설정
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockRestaurantRepository();

        this.restaurantService = new RestaurantService(
                restaurantRepository);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
            .id(1004L)
            .name("Bob zip")
            .categoryId(1L)
            .address("서울")
            .build();
        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findAllByAddressContaining("서울")).willReturn(restaurants);
        given(restaurantRepository.findAllByAddressContainingAndCategoryId(
                "서울", 1L)).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
    }

    @Test
    public void getRestaurantsWithNonFiltering() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(), is(1004L));
    }

    @Test
    public void getRestaurantsWithRegionFiltering() {
        List<Restaurant> restaurants = restaurantService.getRestaurants("서울");

        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(), is(1004L));
    }

    @Test
    public void getRestaurantsWithRegionAndCategoryFiltering() {
        List<Restaurant> restaurants = restaurantService.getRestaurants("서울", 1L);

        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(), is(1004L));
        assertThat(restaurant.getCategoryId(), is(1L));
    }

    @Test
    public void getRestaurantWithExisted() {
        Restaurant restaurant = this.restaurantService.getRestaurant(1004L);

        assertThat(restaurant.getId(), is(1004L));
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void getRestaurantWithNonExisted() {
        Restaurant restaurant = this.restaurantService.getRestaurant(404L);
    }
}