package com.coviam.metadata.controller.admin;


import com.coviam.metadata.entity.Category;
import com.coviam.metadata.services.CategoryServices;
import com.coviam.metadata.utility.CategoryInfo;
import com.coviam.metadata.utility.SubCategories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CategoryController {

    @Autowired
    private CategoryServices categoryService;

    @PostMapping("/addCategory")
    public Category addCategory(@RequestParam String categoryName,
                                @RequestParam(required = false) String parentName){
        return categoryService.addCategory(categoryName,parentName);
    }

    @DeleteMapping("/deleteCategory")
    public boolean deleteCategory(@RequestParam String categoryId){

        return categoryService.deleteCategoryById(categoryId);
    }

    @GetMapping("/getAllSubCategory")
    public List<CategoryInfo> getAllSuCategory(@RequestParam(value = "parentName") String parentCategoryName){
        return categoryService.getAllSubCategory(parentCategoryName);
    }

    @GetMapping("/getAllSubCategoryTree")
    public SubCategories getAllSuCategoryTree(@RequestParam(value = "parentName") String parentCategoryName){
        return categoryService.getAllSubCategoryTree(parentCategoryName);
    }

    @GetMapping("/getCompleteTree")
    public List<SubCategories> getCompleteTree(){ return categoryService.getCompleteTree(); }

    @GetMapping("/getAllParents")
    public List<CategoryInfo> getAllParents(){return categoryService.getAllParents();}
}