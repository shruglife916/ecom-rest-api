package com.cogent.ecom.rest.api.service.impl;

import com.cogent.ecom.rest.api.entity.Category;
import com.cogent.ecom.rest.api.repository.CategoryRepository;
import com.cogent.ecom.rest.api.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long catId) {
        // find category by id
        Category category = categoryRepository
                .findById(catId)
                .orElseThrow(() -> new RuntimeException("resource not found"));
        return category;
    }

    @Override
    public Category createCategory(Category newCategory) {
        return categoryRepository.save(newCategory);
    }

    @Override
    public Category updateCategory(Long catId, Category updateCategory) {
        // find category by id
        Category category = categoryRepository
                .findById(catId)
                .orElseThrow(() -> new RuntimeException("resource not found"));
        // set the updated values
        category.setCategoryName(updateCategory.getCategoryName());
        category.setCategoryDescription(updateCategory.getCategoryDescription());
        category.setCategoryImage(updateCategory.getCategoryImage());
        // save the changes
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long catId) {
        // find category by id
        Category category = categoryRepository
                .findById(catId)
                .orElseThrow(() -> new RuntimeException("resource not found"));
        categoryRepository.deleteById(catId);
    }
}
