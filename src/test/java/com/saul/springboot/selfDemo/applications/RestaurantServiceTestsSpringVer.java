package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.MenuItem;
import com.saul.springboot.selfDemo.domain.MenuItemRepository;
import com.saul.springboot.selfDemo.domain.Restaurant;
import com.saul.springboot.selfDemo.domain.RestaurantRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class RestaurantServiceTestsSpringVer {

    // 실제 객체로 테스트
//    @SpyBean(RestaurantRepositoryImpl.class)
//    RestaurantRepository restaurantRepository;
//    @SpyBean(MenuItemRepositoryImpl.class)
//    MenuItemRepository menuItemRepository;
//    @SpyBean(RestaurantService.class)
//    RestaurantService restaurantService;

    // Mock으로 테스트
    @MockBean
    RestaurantRepository restaurantRepository;
    @MockBean
    MenuItemRepository menuItemRepository;

    private RestaurantService restaurantService;

    @Before
    public void setUp() {

        List<Restaurant> restaurants = getMockRestaurants();

        given(restaurantRepository.findAll()).willReturn(restaurants);

        given(restaurantRepository.findById(3333L)).willReturn(Optional.ofNullable(restaurants.get(0)));
        given(restaurantRepository.findById(4444L)).willReturn(Optional.ofNullable(restaurants.get(1)));
        given(restaurantRepository.findById(5555L)).willReturn(Optional.ofNullable(restaurants.get(2)));

        List<MenuItem> menuItems =  getMockMenuItems(3333L, "periperi", "francesinha");
        List<MenuItem> menuItems2 = getMockMenuItems(4444L, "pulledfork", "slider");

        given(menuItemRepository.getMenuItemsById(3333L)).willReturn(menuItems);
        given(menuItemRepository.getMenuItemsById(4444L)).willReturn(menuItems2);

        // Spring Test를 통해 의존성 주입이 안되기 때문에 추가된 녀석2
        this.restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
    }

    private List<MenuItem> getMockMenuItems(long restaurantId, String menu1, String menu2) {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(restaurantId, menu1));
        menuItems.add(new MenuItem(restaurantId, menu2));

        return menuItems;
    }

    private List<Restaurant> getMockRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(3333L, "Nandos", "Seoul"));
        restaurants.add(new Restaurant(4444L, "Manimal", "Itaewon"));
        restaurants.add(new Restaurant(5555L, "Bonasera", "Gangnam"));

        return restaurants;
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

        Optional<Restaurant> restaurant = this.restaurantService.getRestaurantById(3333L);

        // restaurant info 확인
//        assertThat(restaurant.getId(), is(3333L));
//        assertThat(restaurant.getAddress(), is("Seoul"));
//        assertThat(restaurant.getName(), is("Nandos"));
//        assertThat(restaurant.getInfo(), is("Nandos in Seoul"));
//
//        // menuitem info 확인
//        List<MenuItem> menuItems = restaurant.getMenuItems();
//        MenuItem menuItem = menuItems.get(0);
//        MenuItem menuItem2 = menuItems.get(1);
//
//        assertThat(menuItem.getMenuName(), is("periperi"));
//        assertThat(menuItem2.getMenuName(), is("francesinha"));
    }
}
