package com.cogent.ecom.rest.api.repository;

import com.cogent.ecom.rest.api.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
