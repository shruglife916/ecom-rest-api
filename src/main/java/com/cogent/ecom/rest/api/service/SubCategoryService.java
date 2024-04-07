package com.cogent.ecom.rest.api.service;

import com.cogent.ecom.rest.api.entity.SubCategory;

import java.util.List;

public interface SubCategoryService {
    List<SubCategory> getSubCategoriesByCatId(Long catId);

    SubCategory getSubCategoryById(Long catId, Long subId);

    SubCategory createSubCategory(Long catId, SubCategory newSubCategory);

    SubCategory updateSubCategory(Long subId, SubCategory updateSubCategory);

    void deleteSubCategory(Long subId);
}
