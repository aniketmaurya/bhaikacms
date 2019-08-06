package com.coviam.metadata.controller.admin;

import com.coviam.metadata.entity.Crew;
import com.coviam.metadata.entity.Language;
import com.coviam.metadata.services.CategoryServices;
import com.coviam.metadata.services.CrewServices;
import com.coviam.metadata.services.LanguageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/admin")
@RestController
public class LanguageController {

    @Autowired
    private LanguageServices languageServices;

    // todo add edit language methods
    @PutMapping("/addLanguage")
    public ResponseEntity<Language> addLanguage(@RequestBody Language language) {
        return ResponseEntity.ok(languageServices.addLanguage(language));
    }

    @DeleteMapping("/deleteLanguageByName")
    public ResponseEntity<Boolean> deleteLanguageByName(@RequestParam(name = "languageName") String languageName) {
        return ResponseEntity.ok(languageServices.deleteLanguageByName(languageName));
    }

    @GetMapping("/getAllLanguages")
    public ResponseEntity<List<Language>> getAllLanguage() {
        return ResponseEntity.ok(languageServices.getAllLanguage());
    }

    //update language


}