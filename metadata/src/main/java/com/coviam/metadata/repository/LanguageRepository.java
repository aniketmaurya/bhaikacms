package com.coviam.metadata.repository;

import com.coviam.metadata.entity.Language;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends CrudRepository<Language, String> {
}
