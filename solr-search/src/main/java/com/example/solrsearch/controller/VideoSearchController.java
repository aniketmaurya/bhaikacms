package com.example.solrsearch.controller;

import com.example.solrsearch.dto.ProgramDto;
import com.example.solrsearch.entity.Video;
import com.example.solrsearch.service.VideoSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/solrSearch")
@Slf4j
public class VideoSearchController {



    @Autowired
    VideoSearchService videoSearchService;

    @RequestMapping(method = RequestMethod.POST,value="/addVideos")
    public ResponseEntity<Video> addVideos(@RequestBody ProgramDto programDtos) {
        return ResponseEntity.of(Optional.of(videoSearchService.addVideos(programDtos)));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllVideos")
    public ResponseEntity<Page<Video>> getAllVideos(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                                    @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return ResponseEntity.ok(videoSearchService.getAllVideos(pageNumber, pageSize));
    }

    @DeleteMapping(value = "/deleteAllVideos")
    public boolean deleteAllVideos() {
        return videoSearchService.deleteAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public Page<Video> searchVideos(@RequestParam(value = "searchTerm", defaultValue = "*:*") String searchTerm,
                                    @RequestParam(value = "categoryFilter", defaultValue = "home") String categoryFilter,
                                    @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                    @RequestParam(value = "pageSize", defaultValue = "5") int pageSize
    ) {
        return videoSearchService.search(searchTerm, categoryFilter, pageNumber, pageSize);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/autoComplete")
    public Page<Video> autoSuggest(@RequestParam(value = "word") String word,
                                   @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                   @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return videoSearchService.autoSuggest(word, pageNumber, pageSize);
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/findByProgramId")
//    public Video getByProgramId(@RequestParam String programId) {
//        return videoSearchService.getByProgramId(programId);
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Video update(@RequestBody Video video) {
        return videoSearchService.update(video);
    }

    @DeleteMapping(value = "/deleteById")
    public boolean deleteAllVideos(@RequestParam(value = "id") String id) {
        return videoSearchService.deleteById(id);
    }




}
// server start : ./bin/solr -c