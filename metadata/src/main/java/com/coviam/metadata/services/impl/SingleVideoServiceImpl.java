package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.SingleVideo;
import com.coviam.metadata.repository.SingleVideoRepository;
import com.coviam.metadata.services.SingleVideoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingleVideoServiceImpl implements SingleVideoServices {

    @Autowired
    SingleVideoRepository singleVideoRepository;

    @Override
    public Boolean addSingleVideo(SingleVideo singleVideo) {
        try {
            singleVideoRepository.save(singleVideo);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Boolean deleteSingleVideoById(String singleVideoId) {
        try {
            singleVideoRepository.deleteById(singleVideoId);
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }
}
