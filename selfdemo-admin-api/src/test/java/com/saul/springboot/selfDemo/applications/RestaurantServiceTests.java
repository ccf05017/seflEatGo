package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.*;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class RestaurantServiceTests {

    // 얘만으로는 DI 테스트를 할 수 없다
    private RestaurantService restaurantService;

    @Mock
    private RestaurantRepository restaurantRepository;

    @Mock
    private ItemMenuRepository itemMenuRepository;

    @Mock
    private ReviewRepository reviewRepository;

    // Test 실행하기 전에 미리 수행되는 동작
    // DI를 직접 해주도록 설정
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        mockRestaurantRepository();
        mockItemMenuRepository();
        mockReviewRepository();

        this.restaurantService = new RestaurantService(
                restaurantRepository, itemMenuRepository, reviewRepository);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
            .id(1004L)
            .name("Bob zip")
            .address("Seoul")
            .build();
        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));
    }

    private void mockItemMenuRepository() {
        List<ItemMenu> itemMenus = new ArrayList<>();
        itemMenus.add(ItemMenu.builder()
            .name("Kimchi")
            .build());

        given(itemMenuRepository.findAllByRestaurantId(1004L))
                .willReturn(itemMenus);
    }

    private void mockReviewRepository() {
        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder()
            .id(1L)
            .writer("poppo")
            .description("JMTGR")
            .score(3)
            .restaurantId(1004L)
            .build());

        given(reviewRepository.findAllByRestaurantId(1004L))
            .willReturn(reviews);
    }

    @Test
    public void getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);
        Assert.assertThat(restaurant.getId(), CoreMatchers.is(1004L));
    }

    @Test
    public void getRestaurantWithExisted() {
        Restaurant restaurant = this.restaurantService.getRestaurant(1004L);

        Assert.assertThat(restaurant.getId(), CoreMatchers.is(1004L));

        ItemMenu itemMenu = restaurant.getItemMenus().get(0);
        Review review = restaurant.getReviews().get(0);

        Assert.assertThat(itemMenu.getName(), CoreMatchers.is("Kimchi"));
        Assert.assertThat(review.getDescription(), CoreMatchers.is("JMTGR"));
    }

    @Test(expected = RestaurantNotFoundException.class)
    public void getRestaurantWithNonExisted() {
        Restaurant restaurant = this.restaurantService.getRestaurant(404L);
    }

    @Test
    public void addRestaurant() {

        Restaurant restaurant = Restaurant.builder()
            .name("Beryong")
            .address("Seoul")
            .build();
        Restaurant saved = Restaurant.builder()
            .id(1234L)
            .name("Beryong")
            .address("Seoul")
            .build();

        given(restaurantRepository.save(any())).willReturn(saved);

        Restaurant created = restaurantService.addRestaurant(restaurant);

        Assert.assertThat(created.getId(), CoreMatchers.is(1234L));
    }

    @Test
    public void updateRestaurant() {

        Restaurant restaurant = Restaurant.builder()
            .id(1L)
            .name("sushidama")
            .address("mokdong")
            .build();
        given(restaurantRepository.findById(1L)).willReturn(Optional.of(restaurant));

        restaurantService.updateRestaurant(1L, "blabla grill", "seoul");

        assertThat(restaurant.getId(), is(1L));
        assertThat(restaurant.getName(), is("blabla grill"));

    }
}