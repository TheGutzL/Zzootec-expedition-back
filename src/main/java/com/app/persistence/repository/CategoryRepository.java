package com.app.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.persistence.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

}
