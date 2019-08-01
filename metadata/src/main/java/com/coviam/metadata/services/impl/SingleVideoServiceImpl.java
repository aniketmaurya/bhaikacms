package com.coviam.metadata.services.impl;

import com.coviam.metadata.dto.SingleVideoDto;
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
    public boolean addSingleVideo(SingleVideoDto singleVideoDto) {
        SingleVideo singleVideo = new SingleVideo();
        return false;
    }
}
