package com.bhaikacms.addCrewAndLang.controller;

import com.bhaikacms.addCrewAndLang.dto.CrewDto;
import com.bhaikacms.addCrewAndLang.dto.CrewInsertedDto;
import com.bhaikacms.addCrewAndLang.dto.LanguageDto;
import com.bhaikacms.addCrewAndLang.dto.LanguageInsertedDto;
import com.bhaikacms.addCrewAndLang.services.AddCrew;
import com.bhaikacms.addCrewAndLang.services.AddLanguage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("/add")
public class AddOperationController {

    @Autowired
    AddCrew addCrew;

    @Autowired
    AddLanguage addLanguage;

    @PostMapping(value = "/addCrew")
    public ResponseEntity<?> addCrew(@RequestBody CrewDto crewDto){
        boolean serviceResponse = true;
        CrewInsertedDto crewInsertedDto = new CrewInsertedDto();
        log.info("getCrewName :{} ",crewDto.getCrewName());
        if(crewDto.getCrewName()==null) {
            serviceResponse = false;
        }else {
            serviceResponse = addCrew.addCrewDetails(crewDto);
        }
        if(serviceResponse){
            crewInsertedDto.setCrewName(crewDto.getCrewName());
            log.info("crew details inserted to DB.");
            crewInsertedDto.setMsg("Crew details inserted to DB.");
            return new ResponseEntity<>(crewInsertedDto, HttpStatus.OK);
        }
        else{
            crewInsertedDto.setCrewName(crewDto.getCrewName());
            log.info("Crew details already exist in DB.");
            crewInsertedDto.setMsg("Crew details already exist in DB.");
            return new ResponseEntity<>(crewInsertedDto, HttpStatus.OK);
        }
    }

    @PostMapping(value = "/addLanguage")
    public ResponseEntity<?> addLanguage(@RequestBody LanguageDto languageDto){
        boolean serviceResponse = true;
        LanguageInsertedDto languageInsertedDto = new LanguageInsertedDto();
        log.info("getLanguageName :{} ",languageDto.getLanguageName());
        if(languageDto.getLanguageName()==null) {
            serviceResponse = false;
        }else {
            serviceResponse = addLanguage.addLanguageDetails(languageDto);
        }
        if(serviceResponse){
            languageInsertedDto.setLanguageName(languageDto.getLanguageName());
            log.info("Language details inserted to DB.");
            languageInsertedDto.setMsg("Language details inserted to DB.");
            return new ResponseEntity<>( languageInsertedDto, HttpStatus.OK);
        }
        else{
            languageInsertedDto.setLanguageName(languageDto.getLanguageName());
            log.info("Language details already exists in DB.");
            languageInsertedDto.setMsg("Language details already exists in DB.");
            return new ResponseEntity<>(languageInsertedDto, HttpStatus.OK);
        }
    }
}
