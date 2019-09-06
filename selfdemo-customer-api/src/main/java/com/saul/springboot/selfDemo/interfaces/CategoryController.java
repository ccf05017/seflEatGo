package com.saul.springboot.selfDemo.interfaces;

import com.saul.springboot.selfDemo.applications.CategoryService;
import com.saul.springboot.selfDemo.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> list() {

        return categoryService.getCategories();
    }
}
