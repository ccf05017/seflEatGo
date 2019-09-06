package com.saul.springboot.selfDemo.applications;

import com.saul.springboot.selfDemo.domain.Category;
import com.saul.springboot.selfDemo.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;

    }

    public List<Category> getCategories() {

        return categoryRepository.findAll();
    }

    public Category addCategory(Category resource) {

        return categoryRepository.save(resource);
    }
}
