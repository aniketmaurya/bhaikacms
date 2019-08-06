package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.Language;
import com.coviam.metadata.repository.LanguageRepository;
import com.coviam.metadata.services.LanguageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageServices {


    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public List<Language> addLanguage(List<Language> language) {
        return (List<Language>) languageRepository.saveAll(language);
    }

    @Override
    public Boolean deleteLanguageByName(String name) {
        languageRepository.deleteByName(name);
        return Boolean.TRUE;
    }

    @Override
    public List<Language> getAllLanguage() {
        return languageRepository.findAll();
    }
}
