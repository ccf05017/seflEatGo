package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.ItemMenu;
import com.saul.springboot.selfDemo.domain.ItemMenuRepository;
import com.saul.springboot.selfDemo.domain.Restaurant;
import com.saul.springboot.selfDemo.domain.RestaurantRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class RestaurantServiceTests {

    // 얘만으로는 DI 테스트를 할 수 없다
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private ItemMenuRepository itemMenuRepository;

    // Test 실행하기 전에 미리 수행되는 동작
    // DI를 직접 해주도록 설정
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockRestaurantRepository();
        mockItemMenuRepository();

        this.restaurantService = new RestaurantService(
                restaurantRepository, itemMenuRepository);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");
        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
    }

    private void mockItemMenuRepository() {
        List<ItemMenu> itemMenus = new ArrayList<>();
        itemMenus.add(new ItemMenu("Kimchi"));

        given(itemMenuRepository.findAllByRestaurantId(1004L))
                .willReturn(itemMenus);
    }

    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);
        Assert.assertThat(restaurant.getId(), CoreMatchers.is(1004L));
    }

    @Test
    public void getRestaurant() {
        Restaurant restaurant = this.restaurantService.getRestaurant(1004L);

        Assert.assertThat(restaurant.getId(), CoreMatchers.is(1004L));

        ItemMenu itemMenu = restaurant.getItemMenus().get(0);
        Assert.assertThat(itemMenu.getName(), CoreMatchers.is("Kimchi"));
    }

    @Test
    public void addRestaurant() {

        Restaurant restaurant = new Restaurant("Beryong", "Seoul");
        Restaurant saved = new Restaurant(1234L, "Beryong", "Seoul");

        given(restaurantRepository.save(any())).willReturn(saved);

        Restaurant created = restaurantService.addRestaurant(restaurant);

        Assert.assertThat(created.getId(), CoreMatchers.is(1234L));
    }
}