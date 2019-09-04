package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.ItemMenuService;
import com.saul.springboot.selfDemo.domain.ItemMenu;
import com.saul.springboot.selfDemo.domain.ItemMenuNotFoundException;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ItemMenuController.class)
public class ItemMenuControllerTests {

    @Autowired
    MockMvc mvc;

    @MockBean
    ItemMenuService itemMenuService;

    @Test
    public void itemMenuList() throws Exception {
        List<ItemMenu> itemMenus = new ArrayList<>();
        itemMenus.add(ItemMenu.builder().name("sushi").build());

        given(itemMenuService.getItemMenus(eq(1L)))
                .willReturn(itemMenus);

        mvc.perform(get("/restaurants/1/itemMenus"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"sushi\"")));
    }

    @Test
    public void bulkUpdateWithValidItemMenu() throws Exception {
        mvc.perform(patch("/restaurants/1/itemMenus")
            .contentType(MediaType.APPLICATION_JSON)
            .content("[]"))
            .andExpect(status().isOk());

        verify(itemMenuService).bulkUpdate(eq(1L), any());
    }

    @Test
    public void bulkUpdateWithInvalidItemMenu() throws Exception {

        doThrow(new ItemMenuNotFoundException(444L)).when(itemMenuService).bulkUpdate(eq(444L), any());

        mvc.perform(patch("/restaurants/444/itemMenus")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[]"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("No ItemMenu found in this restaurant")));
    }

}
