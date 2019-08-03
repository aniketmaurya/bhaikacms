package com.example.solrsearch.service.Imp;

import com.example.solrsearch.entity.Video;
import com.example.solrsearch.repository.VideoRepository;
import com.example.solrsearch.service.VideoSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoSearchServiceImp implements VideoSearchService {


    @Autowired
    VideoRepository videoRepository;


    @Override
    public boolean addVideos(List<Video> videos) {

        Iterable<Video> videoList = videoRepository.saveAll(videos);
        return videoList != null;
    }


    @Override
    public Page<Video> getAllVideos(int pageNumber, int pageSize) {
//        return new PageImpl<>(videoRepository.findAll(PageRequest.of(pageNumber, pageSize)).getContent().stream().map(video -> {
//            return VideoDto.builder()
//                    .categoriesList(video.getCategoriesList())
//                    .crewList(video.getCrewList())
//                    .keywords(video.getKeywords())
//                    .languages(video.getLanguages())
//                    .programDescription(video.getProgramDescription())
//                    .programId(video.getProgramId())
//                    .programName(video.getProgramName())
//                    .videoType(video.getVideoType())
//                    .videoUrl(video.getVideoUrl()).build();
//        }).collect(Collectors.toList()));
        return videoRepository.getAllVideos(PageRequest.of(pageNumber, pageSize));
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

//    @Override
//    public Page<Video> searchContaining(String searchTerm, int pageNumber, int pageSize) {
//        return videoRepository.searchContaining(searchTerm, PageRequest.of(pageNumber, pageSize));
//    }

    @Override
    public Video update(Video video) {
        //String video = video.getProgramId();

        return null;

    }

    @Override
    public Video getByProgramId(String programId) {
        return videoRepository.findByProgramId(programId);
    }
}
