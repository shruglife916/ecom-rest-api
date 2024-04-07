package com.cogent.ecom.rest.api.controller;

import com.cogent.ecom.rest.api.entity.SubCategory;
import com.cogent.ecom.rest.api.service.SubCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/v1/category")
@RestController
public class SubCategoryController {
    @Autowired
    private SubCategoryService subCategoryService;

    @PostMapping("/{categoryId}/subCategories")
    public ResponseEntity<SubCategory> createSubCategory(@PathVariable("categoryId") Long catId,
                                                         @Valid @RequestBody SubCategory subCategory) {
        SubCategory data = subCategoryService.createSubCategory(catId, subCategory);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}/subCategories")
    public ResponseEntity<List<SubCategory>> getSubCategoriesByCategoryId(@PathVariable("categoryId") Long catId) {
        List<SubCategory> data = subCategoryService.getSubCategoriesByCatId(catId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/{subCategoryId}")
    public ResponseEntity<SubCategory> getSubCategoryById(@PathVariable("categoryId") Long catId,
                                                          @PathVariable("subCategoryId") Long subId) {
        SubCategory data = subCategoryService.getSubCategoryById(catId, subId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("/{categoryId}/{subCategoryId}")
    public ResponseEntity<SubCategory> updateSubCategory(@PathVariable("categoryId") Long catId,
                                                         @PathVariable("subCategoryId") Long subId,
                                                         @Valid @RequestBody SubCategory subCategory) {
        SubCategory data = subCategoryService.updateSubCategory(subId, subCategory);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}/{subCategoryId}")
    public ResponseEntity<String> deleteSubCategory(@PathVariable("categoryId") Long categoryId,
                                                    @PathVariable("subCategoryId") Long subId) {
        subCategoryService.deleteSubCategory(subId);
        return new ResponseEntity<>("Subcategory deleted successfully", HttpStatus.OK);
    }
}
