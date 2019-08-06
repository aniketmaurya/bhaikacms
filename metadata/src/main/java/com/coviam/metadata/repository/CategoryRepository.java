package com.coviam.metadata.repository;

import com.coviam.metadata.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {

    @Query(value = "Select * from public.category WHERE category_name = :categoryName", nativeQuery = true)
    Category findCategoryByCategoryName(@Param("categoryName") String categoryName);


    @Query(value = "SELECT * FROM public.category WHERE parent_id = :parentId", nativeQuery = true)
    List<Category> findChildByCategoryId(@Param("parentId") String parentId);

    @Query(value = "SELECT * FROM public.category WHERE parent_id is NULL", nativeQuery = true)
    List<Category> findAllParents();


    // shivam
    @Query(value = "SELECT * FROM public.category WHERE category_name = ?1", nativeQuery = true)
    Category getCategoryByCategoryName(String categoryName);

}
