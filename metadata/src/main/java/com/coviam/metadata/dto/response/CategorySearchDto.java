package com.coviam.metadata.dto.response;

import com.coviam.metadata.entity.Category;

import java.util.List;

public class CategorySearchDto {
    String categoryId;
    String categoryName;
    List<Category> subCategory;
}
