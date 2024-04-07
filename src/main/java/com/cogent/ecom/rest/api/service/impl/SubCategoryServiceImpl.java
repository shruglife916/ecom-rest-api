package com.cogent.ecom.rest.api.service.impl;

import com.cogent.ecom.rest.api.entity.Category;
import com.cogent.ecom.rest.api.entity.SubCategory;
import com.cogent.ecom.rest.api.exception.EcomApiException;
import com.cogent.ecom.rest.api.repository.CategoryRepository;
import com.cogent.ecom.rest.api.repository.SubCategoryRepository;
import com.cogent.ecom.rest.api.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Override
    public List<SubCategory> getSubCategoriesByCatId(Long catId) {
        List<SubCategory> subCategories = subCategoryRepository.findByCategoryId(catId);
        return subCategories;
    }

    @Override
    public SubCategory getSubCategoryById(Long catId, Long subId) {
        Category category = categoryRepository
                .findById(catId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        SubCategory subCategory = subCategoryRepository
                .findById(subId)
                .orElseThrow(() -> new RuntimeException("Subcategory not found"));
        if(!subCategory.getCategory().getCatId().equals(category.getCatId()))
            throw new EcomApiException(HttpStatus.BAD_REQUEST, "SubCategory does not belong to this category");
        return subCategory;
    }

    @Override
    public SubCategory createSubCategory(Long catId, SubCategory newSubCategory) {
        Category category = categoryRepository
                .findById(catId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        newSubCategory.setCategory(category);
        return subCategoryRepository.save(newSubCategory);
    }

    @Override
    public SubCategory updateSubCategory(Long subId, SubCategory updateSubCategory) {
        SubCategory subCategory = subCategoryRepository
                .findById(subId)
                .orElseThrow(() -> new RuntimeException("Subcategory not found"));
        subCategory.setCategory(updateSubCategory.getCategory());
        subCategory.setSubCategoryName(updateSubCategory.getSubCategoryName());
        subCategory.setSubCategoryImage(updateSubCategory.getSubCategoryImage());
        subCategory.setSubCategoryDescription(updateSubCategory.getSubCategoryDescription());
        subCategory.setStatus(updateSubCategory.getStatus());
        subCategory.setPosition(updateSubCategory.getPosition());
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public void deleteSubCategory(Long subId) {
        SubCategory subCategory = subCategoryRepository
                .findById(subId)
                .orElseThrow(() -> new RuntimeException("Subcategory not found"));
        subCategoryRepository.delete(subCategory);
    }
}
