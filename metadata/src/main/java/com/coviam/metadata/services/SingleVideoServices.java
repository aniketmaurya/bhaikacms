package com.coviam.metadata.services;

import com.coviam.metadata.entity.SingleVideo;

public interface SingleVideoServices {

    Boolean addSingleVideo(SingleVideo singleVideo);

    Boolean deleteSingleVideoById(String singleVideoId);
}
