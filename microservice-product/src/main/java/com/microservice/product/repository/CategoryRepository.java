package com.microservice.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.product.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
