package com.example.solrsearch.service.Imp;

import com.example.solrsearch.entity.Video;
import com.example.solrsearch.repository.VideoRepository;
import com.example.solrsearch.service.VideoSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class VideoSearchServiceImp implements VideoSearchService {


    @Autowired
    VideoRepository videoRepository;


    @Override
    public boolean addVideos(Iterable<Video> videos) {
        Iterable<Video> videoList = videoRepository.saveAll(videos);
        return videoList != null;
    }


    @Override
    public Page<Video> getAllVideos(int pageNumber, int pageSize) {
        return videoRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public boolean deleteAll() {
        videoRepository.deleteAll();
        return true;
    }

    @Override
    public Page<Video> search(String searchTerm, int pageNumber, int pageSize) {
        return videoRepository.search(searchTerm, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<Video> searchContaining(String searchTerm, int pageNumber, int pageSize) {
        return videoRepository.searchContaining(searchTerm, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Video update(Video video) {
        String contentId = video.getProgramId();
        return null;

    }

    @Override
    public Video getByProgramId(String programId) {
        return videoRepository.findByProgramId(programId);
    }
}
