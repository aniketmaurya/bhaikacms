package com.coviam.metadata.repository;

import com.coviam.metadata.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String> {
    List<Category> findAll();
}
