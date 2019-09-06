package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.CategoryService;
import com.saul.springboot.selfDemo.domain.Category;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    CategoryService categoryService;

    @Test
    public void list() throws Exception {

        List<Category> categories = new ArrayList<>();
        categories.add(Category.builder().name("양식").build());
        given(categoryService.getCategories()).willReturn(categories);

        mvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"양식\"")))
        ;

    }

    @Test
    public void create() throws Exception {

        Category category = Category.builder().id(1L).build();
        given(categoryService.addCategory(any())).willReturn(category);

        mvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"양식\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().stringValues("Location", "/categories/1"))
                .andExpect(content().string(containsString("{}")))
        ;

    }

}
