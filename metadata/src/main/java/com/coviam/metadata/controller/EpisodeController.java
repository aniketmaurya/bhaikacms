package com.coviam.metadata.controller;

import com.coviam.metadata.entity.Episode;
import com.coviam.metadata.services.EpisodeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metadata")
@CrossOrigin
public class EpisodeController {

    @Autowired
    private EpisodeServices episodeService;

    @PutMapping("/addEpisodes")
    ResponseEntity<List<Episode>> addEpisodes(@RequestBody List<Episode> episodes) {
        return ResponseEntity.ok(episodeService.addEpisodes(episodes));
    }

    @DeleteMapping("/deleteEpisode")
    ResponseEntity<Boolean> deleteEpisode(@RequestParam(value = "episodeId") String episodeId) {
        return ResponseEntity.ok(episodeService.deleteEpisode(episodeId));
    }

    @GetMapping("/episodesBySeasonId")
    ResponseEntity<Page<Episode>> getEpisodesBySeasonId(@RequestParam(name = "seasonId") String seasonId,
                                                        @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(episodeService.getEpisodesBySeasonId(seasonId, pageNumber, pageSize));
    }
}