package com.ogulcan.spring_data_jpa.repository;

import com.ogulcan.spring_data_jpa.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
