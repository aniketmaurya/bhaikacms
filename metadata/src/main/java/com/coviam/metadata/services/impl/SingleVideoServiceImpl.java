package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.SingleVideo;
import com.coviam.metadata.repository.SingleVideoRepository;
import com.coviam.metadata.services.SingleVideoServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SingleVideoServiceImpl implements SingleVideoServices {

    @Autowired
    private SingleVideoRepository singleVideoRepository;

    @Override
    public SingleVideo addEpisodes(SingleVideo singleVideo) {
        return singleVideoRepository.save(singleVideo);
    }

    @Override
    public Boolean deleteEpisode(String videoId) {
        singleVideoRepository.deleteById(videoId);
        return Boolean.TRUE;
    }
}
