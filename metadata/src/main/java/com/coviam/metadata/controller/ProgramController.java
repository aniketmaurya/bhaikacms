package com.coviam.metadata.controller;

import com.coviam.metadata.entity.Program;
import com.coviam.metadata.request.ProgramRequest;
import com.coviam.metadata.response.ProgramResponse;
import com.coviam.metadata.services.ProgramServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProgramController {


    @Autowired
    private ProgramServices programServices;


    @GetMapping("/addProgram")
    public ResponseEntity<ProgramResponse> addProgram(@RequestBody ProgramRequest programRequest) {
        Program program = new Program();
        BeanUtils.copyProperties(programRequest, program);
        ProgramResponse response = programServices.addProgram(program);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//    //todo param : Done
//    @DeleteMapping("/deleteProgramById/{programId}")
//    public ResponseEntity<Boolean> deleteProgramById(@RequestParam(name = "programId") String programId) {
//        Boolean response = programServices.deleteProgramById(programId);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
