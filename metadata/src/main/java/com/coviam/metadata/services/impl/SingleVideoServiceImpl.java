package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.SingleVideo;
import com.coviam.metadata.repository.SingleVideoRepository;
import com.coviam.metadata.response.SingleVideoResponse;
import com.coviam.metadata.services.SingleVideoServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SingleVideoServiceImpl implements SingleVideoServices {

    @Autowired
    SingleVideoRepository singleVideoRepository;

    @Override
    public SingleVideoResponse addSingleVideo(SingleVideo singleVideo) {
        SingleVideoResponse singleVideoResponse = SingleVideoResponse
                .builder()
                .singleVideo(singleVideo)
                .build();
        try {
            SingleVideo singleVideo1 = singleVideoRepository.save(singleVideo);
            SingleVideoResponse.builder().isSuccessful(Boolean.TRUE);

            return singleVideoResponse;

        } catch (Exception e) {
            log.error("Error in adding single video");

        }
        return singleVideoResponse;
    }

    @Override
    public Boolean deleteSingleVideoById(String singleVideoId) {
        try {
            singleVideoRepository.deleteById(singleVideoId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
