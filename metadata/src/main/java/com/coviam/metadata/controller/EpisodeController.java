package com.coviam.metadata.controller;

import com.coviam.metadata.entity.Episode;
import com.coviam.metadata.services.impl.EpisodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/metadata")
@CrossOrigin
public class EpisodeController {

    @Autowired
    EpisodeServiceImpl episodeService;

    @PostMapping("/addEpisodes")
    ResponseEntity<?> addEpisodes(@RequestBody List<Episode> episodes) {
        return ResponseEntity.ok(episodeService.addEpisodes(episodes));
    }

    @DeleteMapping("/deleteEpisode")
    ResponseEntity<?> deleteEpisode(@RequestParam(value = "episodeId") String episodeId) {
        return ResponseEntity.ok(episodeService.deleteEpisode(episodeId));
    }

    @GetMapping("/episodesBySeasonId")
    ResponseEntity<?> getEpisodesBySeasonId(@RequestBody String seasonId, int pageNumber, int pageSize) {
        return ResponseEntity.ok(episodeService.getEpisodesBySeasonId(seasonId, pageNumber, pageSize));
    }
}
