package com.bhaikacms.addCrewAndLang.services.impl;

import com.bhaikacms.addCrewAndLang.dto.CrewDto;
import com.bhaikacms.addCrewAndLang.dto.LanguageDto;
import com.bhaikacms.addCrewAndLang.entity.Crew;
import com.bhaikacms.addCrewAndLang.entity.Language;
import com.bhaikacms.addCrewAndLang.repository.LanguageRepository;
import com.bhaikacms.addCrewAndLang.services.AddLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddLanguageImpl implements AddLanguage {

    @Autowired
    LanguageRepository languageRepository;
    @Override
    public boolean addLanguageDetails(LanguageDto languageDto) {
        Language language=new Language();
        language.setLanguageName(languageDto.getLanguageName());
        boolean  result = (languageRepository.findByLanguageName(languageDto.getLanguageName())==null?false:true);
        System.out.println("result : "+result);
        if(result){
            return false;
        }
        else{
            languageRepository.save(language);
            return true;
        }
    }
}
