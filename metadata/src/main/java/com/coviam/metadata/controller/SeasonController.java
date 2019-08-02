package com.coviam.metadata.controller;

import com.coviam.metadata.request.SeasonRequest;
import com.coviam.metadata.response.SeasonResponse;
import com.coviam.metadata.services.SeasonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SeasonController {

    @Autowired
    SeasonServices seasonServices;



    

    @GetMapping(value = "getSeasonsByProgramId/")
    public ResponseEntity<List<SeasonResponse>> getSeasonByProgramId(@RequestParam(name = "programId") String programId,
                                                               @RequestParam(name = "page") Integer page,
                                                               @RequestParam(name = "size") Integer size){

        return new ResponseEntity<List<SeasonResponse>>(seasonServices.getSeasonsByProgramId(programId,page,size), HttpStatus.OK);
    }
}
