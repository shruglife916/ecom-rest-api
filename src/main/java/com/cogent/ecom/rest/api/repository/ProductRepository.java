package com.cogent.ecom.rest.api.repository;

import com.cogent.ecom.rest.api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(Long catId);

    List<Product> findBySubCategoryId(Long subId);
}
