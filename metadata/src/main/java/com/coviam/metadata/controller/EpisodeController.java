package com.coviam.metadata.controller;

import com.coviam.metadata.dto.EpisodeDto;
import com.coviam.metadata.entity.Episode;
import com.coviam.metadata.services.impl.EpisodeServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class EpisodeController {
    @Autowired
    EpisodeServiceImpl episodeService;

    @RequestMapping("/addEpisodes")
    ResponseEntity<?> addEpisodes(@RequestBody List<EpisodeDto> episodeDtoList) {
        List<Episode> episodeList = new ArrayList<>();
        for (EpisodeDto episodeDto : episodeDtoList) {
            Episode episode = new Episode();
            BeanUtils.copyProperties(episodeDto, episode);
            episodeList.add(episode);
        }
        Boolean response = episodeService.addEpisodes(episodeList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping("/deleteEpisode")
    ResponseEntity<?> deleteEpisodeByEpisodeId(@RequestParam(value = "episodeId") String episodeId) {
        Boolean response = episodeService.deleteEpisode(episodeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping("/episodesBySeasonId")
    ResponseEntity<?> episodesBySeasonId(@RequestParam(value = "seasonId") String seasonId) {

    }

}
