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
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryServices categoryService;

    @PostMapping("/addCategory")
    public Category addCategory(@RequestParam(name = "categoryName") String categoryName,
                                @RequestParam(name = "parentName", required = false) String parentName) {
        return categoryService.addCategory(categoryName, parentName);
    }

    @DeleteMapping("/deleteCategory")
    public boolean deleteCategory(@RequestParam String categoryId) {

        return categoryService.deleteCategoryById(categoryId);
    }

    @GetMapping("/getAllSubCategory")
    public List<CategoryInfo> getAllSubCategory(@RequestParam(value = "parentName") String parentCategoryName) {
        return categoryService.getAllSubCategory(parentCategoryName);
    }

    @GetMapping("/getAllSubCategoryTree")
    public SubCategories getAllSubCategoryTree(@RequestParam(value = "parentName") String parentCategoryName) {
        return categoryService.getAllSubCategoryTree(parentCategoryName);
    }

    @GetMapping("/getCompleteTree")
    public List<SubCategories> getCompleteTree() {
        return categoryService.getCompleteTree();
    }

    @GetMapping("/getAllParents")
    public List<CategoryInfo> getAllParents() {
        return categoryService.getAllParents();
    }

    @PutMapping("/updateCategory")
    public Category updateCategory(@RequestParam(name = "categoryName") String categoryName,
                                   @RequestParam(name = "newName") String newCategoryName){
        return categoryService.updateCategory(categoryName,newCategoryName);

    }
}