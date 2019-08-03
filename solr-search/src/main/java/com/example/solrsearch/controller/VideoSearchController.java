package com.example.solrsearch.controller;

import com.example.solrsearch.dto.VideoDto;
import com.example.solrsearch.entity.Video;
import com.example.solrsearch.service.VideoSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/solrSearch")
@Slf4j
public class VideoSearchController {



    @Autowired
    VideoSearchService videoSearchService;

    @RequestMapping(method = RequestMethod.POST,value="/addVideos")
    public ResponseEntity<?> addVideos(@RequestBody List<VideoDto> videoDtos) {

        List<Video> videos = new ArrayList<>();

        for (VideoDto videoDto : videoDtos) {
            Video video = new Video();
            videos.add(Video.builder()
                    .categoriesList(videoDto.getCategoriesList())
                    .crewList(videoDto.getCrewList())
                    .keywords(videoDto.getKeywords())
                    .languages(videoDto.getLanguages())
                    .programDescription(videoDto.getProgramDescription())
                    .programId(videoDto.getProgramId())
                    .programName(videoDto.getProgramName())
                    .videoType(videoDto.getVideoType())
                    .videoUrl(videoDto.getVideoUrl()).build());
        }

//        videoDtos.stream().map(videoDto -> {
//
//        })


        try {
            boolean check = videoSearchService.addVideos(videos);
            return new ResponseEntity<Boolean>(check, HttpStatus.OK);
        } catch (NullPointerException e) {
            e.getStackTrace();
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }//interceptor

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
    public Page<Video> searchVideos(@RequestParam(value = "searchTerm") String searchTerm,
                                    @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                    @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return videoSearchService.search(searchTerm, pageNumber, pageSize);
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/searchBYProgramName")
//    public Page<Video> searchContaining(@RequestParam(value = "searchTerm") String searchTerm,
//                                        @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
//                                        @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
//        return videoSearchService.searchContaining(searchTerm, pageNumber, pageSize);
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/findByProgramId")
    public Video getByProgramId(@RequestParam String programId) {
        return videoSearchService.getByProgramId(programId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public Video update(@RequestBody Video video) {
        return videoSearchService.update(video);
    }



}
