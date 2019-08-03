package com.coviam.metadata.services.impl;

import com.coviam.metadata.repository.CategoryRepository;
import com.coviam.metadata.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryServices {

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public Boolean addCategory(String categoryName, String categoryParentId) {
        return null;
    }

    @Override
    public Boolean fetchCategory(String categoryId) {

        categoryRepository.findById(categoryId);
        return null;
    }
}
