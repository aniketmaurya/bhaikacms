package com.coviam.metadata.controller;

import com.coviam.metadata.dto.request.DeleteRequest;
import com.coviam.metadata.dto.request.SingleVideoRequest;
import com.coviam.metadata.entity.SingleVideo;
import com.coviam.metadata.services.SingleVideoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/metadata")
@RestController
public class SingleVideoController {

    @Autowired
    private SingleVideoServices singleVideoServices;

    @PutMapping("/addSingleVideo")
    public ResponseEntity<SingleVideo> addSingleVideo(@RequestBody SingleVideoRequest singleVideoRequest) {
        return ResponseEntity.ok(singleVideoServices.addEpisodes(singleVideoRequest));
    }

    @PostMapping("/deleteSingleVideo")
    public ResponseEntity<Boolean> deleteSingleVideo(@RequestBody DeleteRequest deleteRequest) {
        return ResponseEntity.ok(singleVideoServices.deleteEpisode(deleteRequest));
    }

    @GetMapping("/getAllSingleVideo")
    public ResponseEntity<List<SingleVideo>> getAllSingleVideo() {
        return ResponseEntity.ok(singleVideoServices.getAllSingleVideo());
    }
}
