package com.coviam.metadata.repository;

import com.coviam.metadata.entity.Language;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LanguageRepository extends CrudRepository<Language, String> {

    void deleteByName(String name);

    List<Language> findAll();


}
