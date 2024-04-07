package com.cogent.ecom.rest.api.repository;

import com.cogent.ecom.rest.api.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    List<SubCategory> findByCategoryId(Long catId);
}
