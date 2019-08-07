package com.coviam.metadata.controller;

import com.coviam.metadata.dto.response.SingleVideoResponse;
import com.coviam.metadata.entity.SingleVideo;
import com.coviam.metadata.services.SingleVideoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RequestMapping("/metadata")
@RestController
public class SingleVideoController {

    @Autowired
    private SingleVideoServices singleVideoServices;

    @PutMapping("/addSingleVideo")
    public ResponseEntity<SingleVideo> addSingleVideo(@RequestBody SingleVideo singleVideo) {
        return ResponseEntity.ok(singleVideoServices.addEpisodes(singleVideo));
    }

    @PostMapping("/deleteSingleVideo")
    public ResponseEntity<Boolean> deleteSingleVideo(@RequestParam(name = "id") String singleVideoId) {
        return ResponseEntity.ok(singleVideoServices.deleteEpisode(singleVideoId));
    }

    @GetMapping("/getAllSingleVideo")
    public ResponseEntity<List<SingleVideo>> getAllSingleVideo() {
        return ResponseEntity.ok(singleVideoServices.getAllSingleVideo());
    }

    @RequestMapping(path = "/addSingleVideoInBulk", method = RequestMethod.POST)
    public ResponseEntity<List<SingleVideoResponse>> addSingleVideoInBulk(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String uploadingDir = System.getProperty("user.dir") + "/src/FileUpload/";
        File file = new File(uploadingDir + multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        List<SingleVideoResponse> singleVideoResponseList = singleVideoServices.addSingleVideoByBulkUpload(file);
        file.delete();
        return ResponseEntity.ok(singleVideoResponseList);
    }
}
