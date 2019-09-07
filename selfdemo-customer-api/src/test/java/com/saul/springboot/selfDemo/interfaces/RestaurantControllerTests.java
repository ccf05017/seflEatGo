package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.RestaurantService;
import com.saul.springboot.selfDemo.domain.Restaurant;
import com.saul.springboot.selfDemo.domain.RestaurantNotFoundException;
import org.hamcrest.core.StringContains;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    public void listWithNonFiltering() throws Exception {
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
    public void listWithRegionFiltering() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(Restaurant.builder()
                .id(1004L)
                .name("Bob zip2")
                .address("서울")
                .build());

        given(restaurantService.getRestaurants("서울")).willReturn(restaurants);

        mvc.perform(get("/restaurants?region=서울"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        StringContains.containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        StringContains.containsString("\"name\":\"Bob zip2\"")
                ));
    }

    @Test
    public void detailWithExistData() throws Exception { // 해당 기능은 restaurantService에 의존성 있음

        // Restaurant 테스트 데이터 추가
        Restaurant restaurant = Restaurant.builder()
            .id(1004L)
            .name("Bob zip")
            .address("Seoul")
            .build();

        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);

        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        StringContains.containsString("\"id\":1004")
                ))
                .andExpect(content().string(
                        StringContains.containsString("\"name\":\"Bob zip\"")
                ))
        ;
    }

    @Test
    public void detailWithNonExistData() throws Exception {

        given(restaurantService.getRestaurant(404L)).willThrow(new RestaurantNotFoundException(404L));

        mvc.perform(get("/restaurants/404"))
            .andExpect(status().isNotFound())
            .andExpect(content().string(containsString("{}")));

    }
}
