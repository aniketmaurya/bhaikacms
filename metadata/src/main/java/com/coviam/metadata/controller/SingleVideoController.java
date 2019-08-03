package com.coviam.metadata.controller;

import com.coviam.metadata.entity.SingleVideo;
import com.coviam.metadata.services.SingleVideoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class SingleVideoController {

    @Autowired
    private SingleVideoServices singleVideoServices;

    @GetMapping("/addSingleVideo")
    public ResponseEntity<SingleVideo> addSingleVideo(@RequestBody SingleVideo singleVideo) {
        return ResponseEntity.ok(singleVideoServices.addEpisodes(singleVideo));
    }

    @GetMapping("/deleteSingleVideo")
    public ResponseEntity<Boolean> deleteSingleVideo(@RequestParam(name = "id") String singleVideoId) {
        return ResponseEntity.ok(singleVideoServices.deleteEpisode(singleVideoId));
    }
}
