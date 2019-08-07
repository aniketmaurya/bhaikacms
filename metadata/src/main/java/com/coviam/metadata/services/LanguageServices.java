package com.coviam.metadata.services;

import com.coviam.metadata.entity.Language;

import java.util.List;

public interface LanguageServices {

    List<Language> addLanguage(List<Language> language);

    Boolean deleteLanguageByName(String name);

    List<Language> getAllLanguage();
}
