package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.Category;
import com.coviam.metadata.repository.CategoryRepository;
import com.coviam.metadata.services.CategoryServices;
import com.coviam.metadata.utility.CategoryInfo;
import com.coviam.metadata.utility.SubCategories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(String categoryName, String parentName) {

        Category category = new Category();

        if (parentName == null) {
            category.setParent(null);
            category.setCategoryName(categoryName);
            categoryRepository.save(category);
            return category;
        }

        Category parentCategory = categoryRepository.findCategoryByCategoryName(parentName);
        category.setCategoryName(categoryName);
        category.setParent(parentCategory);
        categoryRepository.save(category);
        return category;
    }

    @Override
    public boolean deleteCategoryById(String categoryId) {

        List<Category> childList = categoryRepository.findChildByCategoryId(categoryId);

        if (childList.isEmpty()) {
            categoryRepository.deleteById(categoryId);
            return true;
        } else {
            boolean isChildDeleted = deleteRec(categoryId);
            if (isChildDeleted) {
                categoryRepository.deleteById(categoryId);
                return true;
            }
        }
        return false;
    }

    private boolean deleteRec(String categoryId) {

        boolean ifDeleted = false;

        List<Category> childList = categoryRepository.findChildByCategoryId(categoryId);

        if (childList.isEmpty()) {
            Optional<Category> category = categoryRepository.findById(categoryId);
            category.ifPresent(value -> categoryRepository.delete(value));
            ifDeleted = true;
        }
        for (Category category : childList) {
            ifDeleted = deleteRec(category.getId());
//            System.out.println("category deleted : "+category.getCategoryName()+" "+ifDeleted);
        }
        return ifDeleted;
    }

    @Override
    public List<CategoryInfo> getAllSubCategory(String parentCategoryName) {
        Category parentCategory = categoryRepository.findCategoryByCategoryName(parentCategoryName);
        String parentId = parentCategory.getId();
        List<Category> categoryList = categoryRepository.findChildByCategoryId(parentId);
        List<CategoryInfo> categoryInfoList = new ArrayList<>();
        for (Category category : categoryList) {
            CategoryInfo categoryInfo = new CategoryInfo(category.getId(), category.getCategoryName());
            categoryInfoList.add(categoryInfo);
        }
        return categoryInfoList;
    }

    @Override
    public SubCategories getAllSubCategoryTree(String parentCategoryName) {
        return getAllSubCategoryTree(parentCategoryName, new ArrayList<SubCategories>());
    }

    private SubCategories getAllSubCategoryTree(String parentCategoryName, ArrayList<SubCategories> subCategories) {
        Category parentCategory = categoryRepository.findCategoryByCategoryName(parentCategoryName);

        if (parentCategory == null) {

            return null;
        }

        String parentId = parentCategory.getId();
        for (Category child : categoryRepository.findChildByCategoryId(parentId)) {
            subCategories.add(getAllSubCategoryTree(child.getCategoryName(), new ArrayList<>()));
        }
        return new SubCategories(parentCategoryName, parentId, subCategories);
    }

    @Override
    public List<SubCategories> getCompleteTree() {
        List<Category> allParents = categoryRepository.findAllParents();
        List<SubCategories> completeTree = new ArrayList<>();
        for (Category parent : allParents) {
            completeTree.add(getAllSubCategoryTree(parent.getCategoryName(), new ArrayList<>()));
        }
        return completeTree;
    }

    @Override
    public List<CategoryInfo> getAllParents() {
        List<Category> categories = categoryRepository.findAllParents();
        List<CategoryInfo> categoryInfos = new ArrayList<>();
        for (Category category : categories) {
            CategoryInfo categoryInfo = new CategoryInfo(category.getId(), category.getCategoryName());
            categoryInfos.add(categoryInfo);
        }
        return categoryInfos;
    }

}