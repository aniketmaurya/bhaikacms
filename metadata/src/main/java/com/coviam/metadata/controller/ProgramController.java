package com.coviam.metadata.controller;

import com.coviam.metadata.dto.request.DeleteRequest;
import com.coviam.metadata.dto.request.ProgramRequest;
import com.coviam.metadata.dto.response.EmailResponse;
import com.coviam.metadata.entity.Program;
import com.coviam.metadata.services.ProgramServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/metadata")
@CrossOrigin
public class ProgramController {

    @Autowired
    private ProgramServices programServices;


    @PutMapping("/addProgram")
    public ResponseEntity<Program> addProgram(@RequestBody ProgramRequest programRequest) {
        return ResponseEntity.ok(programServices.addProgram(programRequest));
    }

    @PutMapping("/editProgram")
    public ResponseEntity<Boolean> editProgram(@RequestBody ProgramRequest programRequest) {
        return ResponseEntity.ok(programServices.editProgram(programRequest));
    }


    @PostMapping("/deleteProgramById")
    public ResponseEntity<Boolean> deleteProgramById(@RequestBody DeleteRequest deleteRequest) {
        return ResponseEntity.ok(programServices.deleteProgramById(deleteRequest));
    }

    @GetMapping(value = "/getProgramById")
    public ResponseEntity<Program> getProgramById(@RequestParam(name = "programId") String programId) {
        return ResponseEntity.ok(programServices.getProgramById(programId));
    }

    // todo optimise getAll{ProgramType}

    @GetMapping(value = "/getAllSingleVideoProgram")
    public ResponseEntity<Page<Program>> getAllSingleVideoProgram(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {

        return ResponseEntity.ok(programServices
                .getAllSingleVideoProgram(pageNumber, pageSize));
    }


    @GetMapping(value = "/getAllSeasonalVideoProgram")
    public ResponseEntity<Page<Program>> getAllSeasonalVideoProgram(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {

        return ResponseEntity.ok(programServices
                .getAllSeasonalVideoProgram(pageNumber, pageSize));
    }

    @GetMapping(value = "/getAllMultiVideoProgram")
    public ResponseEntity<Page<Program>> getAllMultiVideoProgram(
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {

        return ResponseEntity.ok(programServices
                .getAllMultiVideoProgram(pageNumber, pageSize));
    }

    @GetMapping(value = "/getExpiredVideoList")
    public ResponseEntity<Optional<List<EmailResponse>>> getExpiredVideos() {
        return ResponseEntity.ok(Optional.of(programServices.sendExpiredToEmail()));
    }
    @GetMapping(value = "/getAboutToExpire")
    public ResponseEntity<Optional<List<EmailResponse>>> getAboutToExpire() {
        return ResponseEntity.ok(Optional.of(programServices.sendAboutToExpire()));
    }
}