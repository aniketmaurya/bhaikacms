package com.coviam.metadata.controller;

import com.coviam.metadata.dto.request.ProgramRequest;
import com.coviam.metadata.entity.Program;
import com.coviam.metadata.services.ProgramServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/metadata")
@CrossOrigin
public class ProgramController {

    @Autowired
    private ProgramServices programServices;


    @PutMapping("/addProgram")
    public ResponseEntity<Program> addProgram(@RequestBody ProgramRequest programRequest) {
        Program program = new Program();
        BeanUtils.copyProperties(programRequest, program);
        return ResponseEntity.ok(programServices.addProgram(program));
    }

    @PutMapping("/editProgram")
    public ResponseEntity<Boolean> editProgram(@RequestBody ProgramRequest programRequest) {
        return ResponseEntity.ok(programServices.editProgram(programRequest));
    }


    @DeleteMapping("/deleteProgramById")
    public ResponseEntity<Boolean> deleteProgramById(@RequestParam(name = "programId") String programId) {
        return ResponseEntity.ok(programServices.deleteProgramById(programId));
    }

    @GetMapping(value = "/getProgramById")
    public ResponseEntity<Program> getProgramById(@RequestParam(name = "programId") String programId) {
        return ResponseEntity.ok(programServices.getProgramById(programId));
    }
}
