package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.RestaurantService;
import com.saul.springboot.selfDemo.domain.ItemMenu;
import com.saul.springboot.selfDemo.domain.Restaurant;
import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTests {

    @Autowired
    private MockMvc mvc;

    // 데이터는 이런식으로 SpyBean 만들어줘야 함 + 인터페이스를 그냥 쌩으로 쓰면 구현체를 몰라서 안된다
    // 어떤 구현체를 쓸지 인자로 넣어줘야 함
    // @SpyBean(RestaurantRepositoryImpl.class)
    @MockBean
    private RestaurantService restaurantService;

    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(Restaurant.builder()
            .id(1004L)
            .name("Bob zip2")
            .address("Seoul")
            .build());

        given(restaurantService.getRestaurants()).willReturn(restaurants);

        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        StringContains.containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        StringContains.containsString("\"name\":\"Bob zip2\"")
                ));
    }

    @Test
    public void detail() throws Exception {

        Restaurant restaurant = Restaurant.builder()
            .id(1004L)
            .name("Bob zip")
            .address("Seoul")
            .build();
        Restaurant restaurant2 = Restaurant.builder()
            .id(2020L)
            .name("PeriPeri Food")
            .address("Seoul")
            .build();

//        restaurant.addItemMenu(new ItemMenu("Kimchi"));

        restaurant.setItemMenus(Arrays.asList(new ItemMenu("Kimchi")));

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);
        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        StringContains.containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        StringContains.containsString("\"name\":\"Bob zip\"")
                ))
                .andExpect(content().string(
                        StringContains.containsString("Kimchi")
                ));

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        StringContains.containsString("\"id\":2020")
                ))
                .andExpect(content().string(
                        StringContains.containsString("\"name\":\"PeriPeri Food\"")
                ));
    }

    @Test
    public void create() throws Exception {
        Restaurant restaurant = Restaurant.builder()
            .id(1234L)
            .name("Beryong")
            .address("Seoul")
            .build();

        given(restaurantService.addRestaurant(any())).willReturn(restaurant);

        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Beryong\", \"address\": \"Seoul\"}"))

                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1234"))
                .andExpect(content().string("{}"));

        verify(restaurantService).addRestaurant(any());
    }

    @Test
    public void update() throws Exception {
        mvc.perform(patch("/restaurants/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\": \"modified\", \"address\": \"space\"}"))

            .andExpect(status().isOk());

        // 이렇게 하면 무조건 돌겠지
//        verify(restaurantService).updateRestaurant(any(), any());
        // 실제 인자를 잘 받는지 테스트를 하자
        verify(restaurantService).updateRestaurant(1L, "modified", "space");
    }
}
