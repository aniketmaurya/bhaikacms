package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.Language;
import com.coviam.metadata.repository.LanguageRepository;
import com.coviam.metadata.services.LanguageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageServices {


    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public Boolean addLanguage(List<Language> languages) {

        for (Language language : languages) {
            if (languageRepository.findByNameIgnoreCaseContaining(language.getName()).size() > 0) {
                return Boolean.FALSE;
            }
        }
        languageRepository.saveAll(languages);
        return Boolean.TRUE;
    }

    @Transactional
    @Override
    public Boolean deleteLanguageByName(String name) {
        languageRepository.deleteByName(name);
        return Boolean.TRUE;
    }

    @Override
    public List<Language> getAllLanguage() {
        return languageRepository.findAll();
    }

    @Override
    public Boolean updateLanguage(String id, String newName) {

        if (!languageRepository.existsById(id))
            return Boolean.FALSE;

        Language language = languageRepository.findById(id).get();
        language.setName(newName);
        languageRepository.save(language);
        return Boolean.TRUE;
    }

}
