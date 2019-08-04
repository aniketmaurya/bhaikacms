package com.coviam.metadata.controller;

import com.coviam.metadata.entity.Season;
import com.coviam.metadata.services.SeasonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/metadata")
@CrossOrigin
public class SeasonController {

    @Autowired
    private SeasonServices seasonServices;

    @PutMapping(value = "/addSeason")
    public ResponseEntity<Season> addSeason(@RequestBody Season season) {
        return ResponseEntity.ok(seasonServices.addSeason(season));
    }

    @DeleteMapping(value = "/deleteSeasonById")
    public ResponseEntity<Boolean> deleteSeasonById(@RequestParam(name = "seasonId") String seasonId) {

        return ResponseEntity.ok(seasonServices.deleteSeasonById(seasonId));
    }

    @GetMapping(value = "/getSeasonById")
    public ResponseEntity<?> getSeasonById(@RequestParam(name = "seasonId") String seasonId) {

        Season season = seasonServices.getSeasonById(seasonId);
        return ResponseEntity.ok(season);
    }


    @GetMapping(value = "/getSeasonsByProgramId")
    public ResponseEntity<?> getSeasonByProgramId(
            @RequestParam(name = "programId") String programId,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {

        Page<Season> seasonPage = seasonServices.getSeasonsByProgramId(programId, page, size);

        if (seasonPage.isEmpty()) {
            return new ResponseEntity<Page<Season>>(seasonPage, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    // todo fix this to with annotations get default values
    @GetMapping("/getAllSeasonalVideo")
    public ResponseEntity<Page<Season>> getAllSeasonalVideo(
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber) {
        return ResponseEntity.ok(seasonServices.getAllSeasons(pageSize, pageNumber));
    }
}
