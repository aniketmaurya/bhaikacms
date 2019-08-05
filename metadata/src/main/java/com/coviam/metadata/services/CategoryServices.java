package com.coviam.metadata.services;

import com.coviam.metadata.entity.Category;
import com.coviam.metadata.utility.CategoryInfo;
import com.coviam.metadata.utility.SubCategories;

import java.util.List;


public interface CategoryServices {

    Category addCategory(String categoryName,String parentName);

    boolean deleteCategoryById(String categoryId);

    //immediate children
    List<CategoryInfo> getAllSubCategory(String parentCategoryName);

    //all children
    SubCategories getAllSubCategoryTree(String parentCategoryName);

    List<SubCategories> getCompleteTree();

    List<CategoryInfo> getAllParents();

}
