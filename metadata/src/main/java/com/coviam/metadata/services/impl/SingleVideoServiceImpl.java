package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.SingleVideo;
import com.coviam.metadata.repository.SingleVideoRepository;
import com.coviam.metadata.services.SingleVideoServices;
import com.coviam.metadata.utility.SearchUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SingleVideoServiceImpl implements SingleVideoServices {

    @Autowired
    private SingleVideoRepository singleVideoRepository;
    @Autowired
    private SearchUtility searchUtility;

    @Override
    public SingleVideo addEpisodes(SingleVideo singleVideo) {
        log.info("Single video added videoName {}", singleVideo.getVideoTitle());
        SingleVideo video = singleVideoRepository.save(singleVideo);
        searchUtility.addSingleVideoToSearch(video);
        return video;
    }

    @Override
    public Boolean deleteEpisode(String videoId) {
        singleVideoRepository.deleteById(videoId);
        return Boolean.TRUE;
    }

    @Override
    public List<SingleVideo> getAllSingleVideo() {
        return singleVideoRepository.findAll();
    }
}
