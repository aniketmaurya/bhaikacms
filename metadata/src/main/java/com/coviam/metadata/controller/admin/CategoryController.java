package com.coviam.metadata.controller.admin;

import com.coviam.metadata.entity.Category;
import com.coviam.metadata.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CategoryController {

    @Autowired
    private CategoryServices categoryServices;

    @GetMapping("/getCategory/{catId}")
    public ResponseEntity<Category> retrieveCategory(@PathVariable(name = "catId") String id) {
        // Getting the requiring category; or throwing exception if not found
        final Category category = categoryServices.getCategoryById(id).orElse(new Category());

        return ResponseEntity.ok(category);
    }
}