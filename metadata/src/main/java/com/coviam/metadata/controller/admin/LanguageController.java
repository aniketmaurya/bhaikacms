package com.coviam.metadata.controller.admin;

import com.coviam.metadata.entity.Language;
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
    @PostMapping("/addLanguage")
    public ResponseEntity<Boolean> addLanguage(@RequestBody List<Language> languages) {
        return ResponseEntity.ok(languageServices.addLanguage(languages));
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
    @PutMapping("/editLanguage")
    public ResponseEntity<?> editName(
            @RequestParam(name = "id") String id,
            @RequestParam(name = "newName") String newName) {

        return ResponseEntity.ok(languageServices.updateLanguage(id, newName));
    }
}