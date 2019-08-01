package com.example.solrsearch.service;

import com.example.solrsearch.entity.Video;

import java.util.List;

public interface VideoSearchService {
    List<Video> addVideos(List<Video> videos);
}
