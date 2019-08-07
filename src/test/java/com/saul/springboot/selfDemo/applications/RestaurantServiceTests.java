package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RestaurantServiceTests {

    // Test를 위한 의존성들
    RestaurantRepository restaurantRepository = new RestaurantRepositoryImpl();
    MenuItemRepository menuItemRepository = new MenuItemRepositoryImpl();

    private RestaurantService restaurantService;

    @Before
    public void setUp() {

        this.restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
    }

    @Test
    public void getRestaurants() {

        List<Restaurant> restaurants = this.restaurantRepository.findAll();
        Restaurant restaurant = restaurants.get(1);

        // restaurant info 확인
        assertThat(restaurant.getName(), is("Manimal"));
        assertThat(restaurant.getId(), is(4444L));
        assertThat(restaurant.getAddress(), is("Itaewon"));
        assertThat(restaurant.getInfo(), is("Manimal in Itaewon"));

        // menuitem info 확인
        List<MenuItem> menuItems = this.menuItemRepository.getMenuItemsById(restaurant.getId());
        MenuItem menuItem = menuItems.get(0);
        MenuItem menuItem2 = menuItems.get(1);

        assertThat(menuItem.getMenuName(), is("pulledfork"));
        assertThat(menuItem.getRestaurantId(), is(4444L));
        assertThat(menuItem2.getMenuName(), is("slider"));
        assertThat(menuItem2.getRestaurantId(), is(4444L));

    }

    @Test
    public void getRestaurantById() {

        Restaurant restaurant = this.restaurantService.getRestaurantById(3333L);

        // restaurant info 확인
        assertThat(restaurant.getId(), is(3333L));
        assertThat(restaurant.getAddress(), is("Seoul"));
        assertThat(restaurant.getName(), is("Nandos"));
        assertThat(restaurant.getInfo(), is("Nandos in Seoul"));

        // menuitem info 확인
        List<MenuItem> menuItems = restaurant.getMenuItems();
        MenuItem menuItem = menuItems.get(0);
        MenuItem menuItem2 = menuItems.get(1);

        assertThat(menuItem.getMenuName(), is("periperi"));
        assertThat(menuItem2.getMenuName(), is("francesinha"));
    }
}
