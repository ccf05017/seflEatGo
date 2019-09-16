package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.DTO.RestaurantFilterDTO;
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
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockRestaurantRepository();

        restaurantService = new RestaurantService(restaurantRepository);
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
        given(restaurantRepository.findAllByCategoryId(1L)).willReturn(restaurants);
        given(restaurantRepository.findAllByAddressContainingAndCategoryId(
                "서울", 1L)).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
    }

//    @Test
//    public void getRestaurantsWithNonFiltering() {
//        String region = null;
//        Long categoryId = null;
//
//        RestaurantFilterDTO mockFilterDTO = RestaurantFilterDTO.builder()
//                .region(region)
//                .categoryId(categoryId)
//                .build();
//
//        List<Restaurant> mockRestaurants = new ArrayList<>();
//        mockRestaurants.add(Restaurant.builder()
//                .address("hello")
//                .id(1004L)
//                .build());
//
//        given(restaurantFilterType.getRestaurantsByFilter(null, null)).willReturn(mockRestaurants);
//
//        List<Restaurant> restaurants = restaurantService.getRestaurants(mockFilterDTO);
//
//        Restaurant restaurant = restaurants.get(0);
//        assertThat(restaurant.getId(), is(1004L));
//        assertThat(restaurant.getAddress(), is("hello"));
//    }
//
//    @Test
//    public void getRestaurantsWithRegionFiltering() {
//        String region = "서울";
//        Long categoryId = null;
//
//        RestaurantFilterDTO mockFilterDTO = RestaurantFilterDTO.builder()
//                .region(region)
//                .categoryId(categoryId)
//                .build();
//
//        List<Restaurant> restaurants = restaurantService.getRestaurants(mockFilterDTO);
//
//        Restaurant restaurant = restaurants.get(0);
//        assertThat(restaurant.getId(), is(1004L));
//        assertThat(restaurant.getAddress(), is("서울"));
//    }
//
//    @Test
//    public void getRestaurantsWithCategoryIdFiltering() {
//        String region = null;
//        Long categoryId = 1L;
//
//        RestaurantFilterDTO mockFilterDTO = RestaurantFilterDTO.builder()
//                .region(region)
//                .categoryId(categoryId)
//                .build();
//
//        List<Restaurant> restaurants = restaurantService.getRestaurants(mockFilterDTO);
//
//        Restaurant restaurant = restaurants.get(0);
//        assertThat(restaurant.getId(), is(1004L));
//        assertThat(restaurant.getCategoryId(), is(1L));
//    }

    @Test
    public void getRestaurantsWithRegionAndCategoryFiltering() {
        String region = "서울";
        Long categoryId = 1L;

        RestaurantFilterDTO mockFilterDTO = RestaurantFilterDTO.builder()
                .region(region)
                .categoryId(categoryId)
                .build();

        List<Restaurant> restaurants = restaurantService.getRestaurants(mockFilterDTO);

        Restaurant restaurant = restaurants.get(0);
        assertThat(restaurant.getId(), is(1004L));
        assertThat(restaurant.getCategoryId(), is(1L));
        assertThat(restaurant.getAddress(), is("서울"));
    }

    @Test
    public void getRestaurantWithExisted() {
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        assertThat(restaurant.getId(), is(1004L));
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void getRestaurantWithNonExisted() {
        Restaurant restaurant = this.restaurantService.getRestaurant(404L);
    }
}