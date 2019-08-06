package com.example.solrsearch.service;

import com.example.solrsearch.dto.ProgramDto;
import com.example.solrsearch.entity.Video;
import org.springframework.data.domain.Page;

public interface VideoSearchService {
    Video addVideos(ProgramDto programDtoList);

    Page<Video> getAllVideos(int pageNumber, int pageSize);

    boolean deleteAll();

    Page<Video> search(String searchTerm, String categoryFilter, int pageNumber, int pageSize);

    Video update(Video video);

    Video getByProgramId(String programId);

    Page<Video> autoSuggest(String word, int pageNumber, int pageSize);
}
