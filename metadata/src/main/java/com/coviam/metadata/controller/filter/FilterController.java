package com.coviam.metadata.controller.filter;

import com.coviam.metadata.services.ProgramServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filter")
@CrossOrigin
public class FilterController {
    @Autowired
    private ProgramServices programServices;


    @GetMapping(value = "/filterProgramByField")
    public ResponseEntity<?> filterProgramByField(
            @RequestParam(name = "fieldName") String fieldName,
            @RequestParam(name = "fieldValue") String fieldValue,
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        if (fieldName.equals("language"))
            return ResponseEntity.ok(programServices.filterByProgramLanguage(pageNumber, pageSize, fieldValue));

        if (fieldName.equals("programType"))
            return ResponseEntity.ok(programServices.filterByProgramType(pageNumber, pageSize, fieldValue));

        if (fieldName.equals("programName"))
            return ResponseEntity.ok(programServices.filterByProgramName(pageNumber, pageSize, fieldValue));

        return ResponseEntity.ok("Filter Name is invalid! It should be language, programType or programName.");
    }


}


//==========================================================
//Filter by program name
//==========================================================
//added by Sunil
    /*@GetMapping(value = "/filterByProgramName")
    public ResponseEntity<Page<Program>> filterByProgramName(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "name", defaultValue = "5") String name
    ) {
        return ResponseEntity.ok(programServices.filterByProgramName(pageNumber, pageSize, name));
    }*/

//==========================================================
//Filter by program type
//==========================================================
//sort in descending order by program name
    /*@GetMapping(value = "/filterByProgramType")
    public ResponseEntity<Page<Program>> filterByProgramType(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "type", defaultValue = "5") String type
    ) {
        return ResponseEntity.ok(programServices.filterByProgramType(pageNumber, pageSize, type));
    }*/

//==========================================================
//Filter by program language
//==========================================================
//Sort by newestFirst
    /*@GetMapping(value = "/filterByProgramLanguage")
    public ResponseEntity<Page<Program>> filterByProgramLanguage(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "languages", defaultValue = "5") String languages
    ) {
        return ResponseEntity.ok(programServices.filterByProgramLanguage(pageNumber, pageSize, languages));
    }*/

