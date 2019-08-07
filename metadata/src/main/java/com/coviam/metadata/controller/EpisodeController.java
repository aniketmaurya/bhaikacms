package com.coviam.metadata.controller;

import com.coviam.metadata.dto.request.DeleteRequest;
import com.coviam.metadata.dto.response.EpisodeResponse;
import com.coviam.metadata.entity.Episode;
import com.coviam.metadata.services.EpisodeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    @PostMapping("/deleteEpisodeById")
    ResponseEntity<Boolean> deleteEpisode(@RequestBody DeleteRequest deleteRequest) {
        return ResponseEntity.ok(episodeService.deleteEpisode(deleteRequest));
    }

    @GetMapping("/getEpisodesBySeasonId")
    ResponseEntity<Page<Episode>> getEpisodesBySeasonId(@RequestParam(name = "seasonId") String seasonId,
                                                        @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                                        @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        return ResponseEntity.ok(episodeService.getEpisodesBySeasonId(seasonId, pageNumber, pageSize));
    }

    @RequestMapping(path = "/addEpisodeInBulk", method = RequestMethod.POST)
    public ResponseEntity<List<EpisodeResponse>> addEpisodeInBulk(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String uploadingDir = System.getProperty("user.dir") + "/src/FileUpload/";
        File file = new File(uploadingDir + multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        List<EpisodeResponse> episodeResponseList = episodeService.addEpisodeByBulkUpload(file);
        file.delete();
        return ResponseEntity.ok(episodeResponseList);
    }
}