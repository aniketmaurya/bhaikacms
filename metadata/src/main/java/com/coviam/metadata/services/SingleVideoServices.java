package com.coviam.metadata.services;

import com.coviam.metadata.entity.SingleVideo;
import com.coviam.metadata.response.SingleVideoResponse;

public interface SingleVideoServices {

    SingleVideoResponse addSingleVideo(SingleVideo singleVideo);

    Boolean deleteSingleVideoById(String singleVideoId);
}
