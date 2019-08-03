package com.coviam.metadata.controller;

import com.coviam.metadata.entity.Season;
import com.coviam.metadata.services.SeasonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
public class SeasonController {

    @Autowired
    private SeasonServices seasonServices;

    @PostMapping(value = "/addSeason")
    public ResponseEntity<Optional<Season>> addSeason(@RequestBody Season season) {
        return new ResponseEntity<>(seasonServices.addSeason(season), HttpStatus.OK);

    }

    @DeleteMapping(value = "/deleteSeasonById")
    public ResponseEntity<Boolean> deleteSeasonById(@RequestParam(name = "seasonId") String seasonId) {

        return new ResponseEntity<>(seasonServices.deleteSeasonById(seasonId), HttpStatus.OK);
    }

    @GetMapping(value = "/getSeasonById")
    public ResponseEntity<?> getSeasonById(@RequestParam(name = "seasonId") String seasonId) {

        Optional<Season> seasonOptional = seasonServices.getSeasonById(seasonId);

        return seasonOptional.map(season -> new ResponseEntity<>(season, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(new Season(), HttpStatus.NO_CONTENT));

    }


    @GetMapping(value = "/getSeasonsByProgramId")
    public ResponseEntity<?> getSeasonByProgramId(@RequestParam(name = "programId") String programId,
                                                  @RequestParam(name = "page", defaultValue = "0") int page,
                                                  @RequestParam(name = "size", defaultValue = "10") int size) {

        Page<Season> seasonPage = seasonServices.getSeasonsByProgramId(programId, page, size);


        if (seasonPage.isEmpty()) {
            return new ResponseEntity<Page<Season>>(seasonPage, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

    }
}
