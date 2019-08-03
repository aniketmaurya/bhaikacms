package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.Category;
import com.coviam.metadata.repository.CategoryRepository;
import com.coviam.metadata.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Boolean addCategory(String categoryName) {
        Category category = new Category();
        category.setName(categoryName);
        categoryRepository.save(category);
        return Boolean.TRUE;
    }

    @Transactional
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Transactional
    @Override
    public Optional<Category> getCategoryById(String id) {
        return categoryRepository.findById(id);
    }


    @Transactional
    @Override
    public void updateCategory(Category category, String name) {
        category.setName(name);
        categoryRepository.save(category);
    }

    @Transactional
    @Override
    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    @Transactional
    @Override
    public boolean isChildCategory(Category category, Category parent) {
        return category.getParent().equals(parent);
    }

    @Transactional
    @Override
    public void addChildCategory(Category category, Category parent) {
        category.setParent(parent);
        categoryRepository.save(category);
    }

    @Transactional
    @Override
    public void removeChildCategory(Category category, Category parent) {
        category.setParent(null);
        categoryRepository.save(category);
    }

}