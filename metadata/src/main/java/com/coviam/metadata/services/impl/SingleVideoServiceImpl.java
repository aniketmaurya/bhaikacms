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
    SingleVideoRepository singleVideoRepository;

    @Override
    public Boolean addSingleVideo(SingleVideo singleVideo) {

        try {
            singleVideoRepository.save(singleVideo);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Error while adding single video with id:{}", singleVideo.getId());
            return false;
        }
        return true;
    }

    @Override
    public Boolean deleteSingleVideoById(String singleVideoId) {
        try {
            singleVideoRepository.deleteById(singleVideoId);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Error while deleting single video with id:{}", singleVideoId);
            return false;
        }
        return true;
    }
}
