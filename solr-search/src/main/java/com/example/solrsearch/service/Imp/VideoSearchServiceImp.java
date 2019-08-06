package com.example.solrsearch.service.Imp;

import com.example.solrsearch.dto.ProgramDto;
import com.example.solrsearch.entity.Category;
import com.example.solrsearch.entity.Video;
import com.example.solrsearch.repository.VideoRepository;
import com.example.solrsearch.service.VideoSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Slf4j
@Service
public class VideoSearchServiceImp implements VideoSearchService {


    @Autowired
    VideoRepository videoRepository;


    @Override
    public Video addVideos(ProgramDto programDto) {
        log.debug("Video is getting added");
        Video video = Video.builder()
                .crewList(programDto.getCrewList())
                .keywords(programDto.getKeywords())
                .languages(programDto.getLanguages())
                .programDescription(programDto.getProgramDescription())
                .programId(programDto.getProgramId())
                .programName(programDto.getProgramName())
                .videoType(programDto.getVideoType())
                .videoUrl(programDto.getVideoUrl())
                .categoriesList(new ArrayList<>()).build();

        Category category = programDto.getCategory();

        if (category != null) {
            do {
                video.getCategoriesList().add(category.getCategoryName());
                category = category.getParent();
            } while (null != category);
        }
        StringBuilder pathVariable = new StringBuilder();
        Collections.reverse(video.getCategoriesList());
        for (String s : video.getCategoriesList()) {
            pathVariable.append(s + "/");
        }
        video.setPath(pathVariable.toString());


        return videoRepository.save(video);
    }

    @Override
    public Page<Video> getAllVideos(int pageNumber, int pageSize) {
        log.debug("Got all videos");
        return videoRepository.getAllVideos(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public boolean deleteAll() {
        log.debug("Deleted");
        videoRepository.deleteAll();
        return true;
    }

    @Override
    public Page<Video> search(String searchTerm, String categoryFilter, int pageNumber, int pageSize) {
        log.debug("Searched");
        return videoRepository.search(searchTerm, categoryFilter, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<Video> autoSuggest(String word, int pageNumber, int pageSize) {
        log.debug("Auto suggest");
        return videoRepository.autoSuggest(word, pageNumber, pageSize);
    }

    @Override
    public Video update(Video video) {

        log.debug("Updated");
        return videoRepository.save(video);

    }

    @Override
    public Video getByProgramId(String programId) {
        return videoRepository.findByProgramId(programId);
    }
}
