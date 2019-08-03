package com.bhaikacms.addCrewAndLang.repository;

import com.bhaikacms.addCrewAndLang.entity.Language;
import org.springframework.data.repository.CrudRepository;

public interface LanguageRepository extends CrudRepository<Language,Integer> {
    Language findByLanguageName(String languageName);
}
