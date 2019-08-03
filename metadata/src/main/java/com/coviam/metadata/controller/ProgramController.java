package com.coviam.metadata.controller;

import com.coviam.metadata.entity.Program;
import com.coviam.metadata.services.ProgramServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ProgramController {

    @Autowired
    private ProgramServices programServices;


    @PutMapping("/addProgram")
    public ResponseEntity<Program> addProgram(@RequestBody Program program) {
        return ResponseEntity.ok(programServices.addProgram(program).orElse(new Program()));
    }

    @DeleteMapping("/deleteProgramById")
    public ResponseEntity<Boolean> deleteProgramById(@RequestParam(name = "programId") String programId) {
        return ResponseEntity.ok(programServices.deleteProgramById(programId));
    }

    @GetMapping(value = "/getProgramById")
    public ResponseEntity<Program> getProgramById(@RequestParam(name = "programId") String programId) {
        return ResponseEntity.ok(programServices.getProgramById(programId).orElse(new Program()));
    }
}