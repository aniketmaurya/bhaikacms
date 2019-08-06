package com.coviam.metadata.services;

import com.coviam.metadata.entity.Language;

import java.util.List;

public interface LanguageServices {

    Boolean addLanguage(List<Language> languages);

    Boolean deleteLanguageByName(String name);

    List<Language> getAllLanguage();

    Boolean updateLanguage(String id, String newName);
}
