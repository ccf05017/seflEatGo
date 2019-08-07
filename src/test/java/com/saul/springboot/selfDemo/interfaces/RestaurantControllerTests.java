package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.domain.MenuItemRepository;
import com.saul.springboot.selfDemo.domain.MenuItemRepositoryImpl;
import com.saul.springboot.selfDemo.domain.RestaurantRepository;
import com.saul.springboot.selfDemo.domain.RestaurantRepositoryImpl;
import com.saul.springboot.selfDemo.services.RestaurantService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTests {

    @Autowired
    MockMvc mvc;

    // 실제 객체 DI를 이용한 테스트 방법
    @SpyBean(RestaurantService.class)
    RestaurantService restaurantService;

    @SpyBean(RestaurantRepositoryImpl.class)
    RestaurantRepository restaurantRepository;

    @SpyBean(MenuItemRepositoryImpl.class)
    MenuItemRepository menuItemRepository;

    @Before
    public void setUp() {

    }

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
}
