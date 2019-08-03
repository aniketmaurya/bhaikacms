package com.coviam.metadata.services;

public interface CategoryServices {


    Boolean addCategory(String categoryName, String categoryParentId);

    Boolean fetchCategory(String categoryId);
}
