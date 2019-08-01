package com.example.solrsearch.service;

import com.example.solrsearch.entity.Video;
import org.springframework.data.domain.Page;

public interface VideoSearchService {
    boolean addVideos(Iterable<Video> videos);

    Page<Video> getAllVideos(int pageNumber, int pageSize);

    boolean deleteAll();

    Page<Video> search(String searchTerm, int pageNumber, int pageSize);

    Page<Video> searchContaining(String searchTerm, int pageNumber, int pageSize);

    Video update(Video video);

    Video getByProgramId(String programId);
}
