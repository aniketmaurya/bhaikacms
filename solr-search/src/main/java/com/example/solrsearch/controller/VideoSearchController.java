package com.example.solrsearch.controller;

import com.example.solrsearch.entity.Employee;
import com.example.solrsearch.entity.Video;
import com.example.solrsearch.service.VideoSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class VideoSearchController {



    @Autowired
    VideoSearchService videoSearchService;

    @RequestMapping(method = RequestMethod.POST,value="/addVideos")
    public ResponseEntity<?> addVideos(@RequestBody List<Video> videos)
    {
        try
        {
            List<Video> videoList =videoSearchService.addVideos(videos);
            return new ResponseEntity<List<Video>>(videoList, HttpStatus.OK);
        }
        catch (NullPointerException e)
        {
            e.getStackTrace();
            return new ResponseEntity<String>(null,HttpStatus.OK);
        }
    }


}
