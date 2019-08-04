package com.coviam.metadata.services;

import com.coviam.metadata.entity.Language;

import java.util.List;

public interface LanguageServices {

    Language addLanguage(Language language);

    Boolean deleteLanguageByName(String name);

    List<Language> getAllLanguage();
}
