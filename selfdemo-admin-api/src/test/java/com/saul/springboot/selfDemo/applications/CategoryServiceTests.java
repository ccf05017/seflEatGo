package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.Category;
import com.saul.springboot.selfDemo.domain.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class CategoryServiceTests {

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    public void getCategories() {
        List<Category> mockCategories = new ArrayList<>();
        mockCategories.add(Category.builder().name("양식").build());
        given(categoryRepository.findAll()).willReturn(mockCategories);

        List<Category> resultCategories = categoryService.getCategories();

        assertThat(resultCategories.get(0).getName()).isEqualTo("양식");
    }

    @Test
    public void addCategory() {
        Category resource = Category.builder().name("양식").build();
        Category mockResult = Category.builder().id(1L).name("양식").build();
        given(categoryRepository.save(resource)).willReturn(mockResult);

        Category saved = categoryService.addCategory(resource);

        assertThat(saved.getId()).isEqualTo(1L);
    }

}
