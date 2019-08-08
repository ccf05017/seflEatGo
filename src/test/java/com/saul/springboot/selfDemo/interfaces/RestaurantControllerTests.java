package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.RestaurantService;
import com.saul.springboot.selfDemo.domain.MenuItem;
import com.saul.springboot.selfDemo.domain.Restaurant;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTests {

    @Autowired
    MockMvc mvc;

    // 실제 객체 DI를 이용한 테스트 방법
//    @SpyBean(RestaurantService.class)
//    RestaurantService restaurantService;
//
//    @SpyBean(RestaurantRepositoryImpl.class)
//    RestaurantRepository restaurantRepository;
//
//    @SpyBean(MenuItemRepositoryImpl.class)
//    MenuItemRepository menuItemRepository;

    // Mock 객체를 이용한 DI 테스트
    // 실제 환경과 다르게 자유롭게 테스트 할 수 있음
    // 대신 Mock 객체 넣느라 아주아주아주아주아주 귀찮아질 수 있음
    @MockBean
    RestaurantService restaurantService;

    @Before
    public void setUp() {
        List<Restaurant> restaurants = getRestaurants();

        List<MenuItem> menuItems = getMenuItems(3333L, "periperi", "francesinha");

        List<MenuItem> menuItems2 = getMenuItems(4444L, "pulledfork", "slider");

        restaurants.get(0).addMenuItems(menuItems);
        restaurants.get(1).addMenuItems(menuItems2);

        // list용 설정
        given(restaurantService.getRestaurants()).willReturn(restaurants);

        // detail용 설정
        given(restaurantService.getRestaurantById(3333L)).willReturn(restaurants.get(0));
        given(restaurantService.getRestaurantById(4444L)).willReturn(restaurants.get(1));
        given(restaurantService.getRestaurantById(5555L)).willReturn(restaurants.get(2));
    }

    // Mock 의존성 설정
    private List<MenuItem> getMenuItems(long restaurantId, String menu1, String menu2) {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(restaurantId, menu1));
        menuItems.add(new MenuItem(restaurantId, menu2));
        return menuItems;
    }

    private List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(3333L, "Nandos", "Seoul"));
        restaurants.add(new Restaurant(4444L, "Manimal", "Itaewon"));
        restaurants.add(new Restaurant(5555L, "Bonasera", "Gangnam"));
        return restaurants;
    }

    // Test Code
    @Test
    public void list() throws Exception {
        mvc.perform(get("/restaurants"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"name\":\"Nandos\"")
            ))
            .andExpect(content().string(
                containsString("\"address\":\"Seoul\"")
            ))
            .andExpect(content().string(
                containsString("\"id\":3333")
            ))
            .andExpect(content().string(
                containsString("\"menuName\":\"periperi\"")
            ))
            .andExpect(content().string(
                    containsString("\"menuName\":\"francesinha\"")
            ))
            .andExpect(content().string(
                    containsString("\"restaurantId\":3333")
            ));
    }

    @Test
    public void detail() throws Exception {
        mvc.perform(get("/restaurants/3333"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                containsString("\"name\":\"Nandos\"")
            ))
            .andExpect(content().string(
                containsString("\"address\":\"Seoul\"")
            ))
            .andExpect(content().string(
                containsString("\"id\":3333")
            ))
            .andExpect(content().string(
                    containsString("\"menuName\":\"periperi\"")
            ))
            .andExpect(content().string(
                containsString("\"menuName\":\"francesinha\"")
            ))
            .andExpect(content().string(
                containsString("\"restaurantId\":3333")
            ));

        mvc.perform(get("/restaurants/4444"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                    containsString("\"name\":\"Manimal\"")
            ))
            .andExpect(content().string(
                    containsString("\"address\":\"Itaewon\"")
            ))
            .andExpect(content().string(
                    containsString("\"id\":4444")
            ))
            .andExpect(content().string(
                    containsString("\"menuName\":\"pulledfork\"")
            ))
            .andExpect(content().string(
                containsString("\"menuName\":\"slider\"")
            ))
            .andExpect(content().string(
                containsString("\"restaurantId\":4444")
            ));

        mvc.perform(get("/restaurants/5555"))
            .andExpect(status().isOk())
            .andExpect(content().string(
                    containsString("\"name\":\"Bonasera\"")
            ))
            .andExpect(content().string(
                    containsString("\"address\":\"Gangnam\"")
            ))
            .andExpect(content().string(
                    containsString("\"id\":5555")
            ));

    }

    @Test
    public void create() throws Exception {
        // Controller 요청이 제대로 돌아가는지 확인하는 test
        mvc.perform(post("/restaurants")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\": \"sushidama\", \"address\": \"mokdong\"}"))
            .andExpect(status().isCreated())
            .andExpect(header().string("location", "/restaurants/5555"))
            .andExpect(content().string(containsString("{}")));

        // 암모턴 뭔가를 넣어서 restaurantService mock 객체에서 addRestaurant()을 실행하는지 확인!
        // 실제 저장하는지 안하는지는 controller가 신경 쓸 일이 아니다.
        // restaurantService가 알아서 해줄거니까 여기서는 저 함수가 제대로 실행되는지만 보증해주면 됨
        verify(restaurantService).addRestaurant(any());
    }
}

