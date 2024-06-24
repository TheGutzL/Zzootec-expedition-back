package com.microservice.category.controller;

import com.microservice.category.models.Category;
import com.microservice.category.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<?> findAllCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @GetMapping("/search-product/{idCategory}")
    public ResponseEntity<?> findProductsByIdCategory(@PathVariable Long idCategory) {
        return ResponseEntity.ok(categoryService.findProductsByIdCategory(idCategory));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCategory(@RequestBody Category category) {
        categoryService.save(category);
    }


}
