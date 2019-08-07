package com.coviam.metadata.services;

import com.coviam.metadata.dto.request.DeleteRequest;
import com.coviam.metadata.dto.request.SingleVideoRequest;
import com.coviam.metadata.entity.SingleVideo;

import java.util.List;

public interface SingleVideoServices {

    SingleVideo addEpisodes(SingleVideoRequest singleVideoRequest);

    Boolean deleteEpisode(DeleteRequest deleteRequest);

    List<SingleVideo> getAllSingleVideo();
}
