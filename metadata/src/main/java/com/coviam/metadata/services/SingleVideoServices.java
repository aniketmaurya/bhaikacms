package com.coviam.metadata.services;

import com.coviam.metadata.dto.response.SingleVideoResponse;
import com.coviam.metadata.entity.SingleVideo;

import java.io.File;
import java.util.List;

public interface SingleVideoServices {

    SingleVideo addEpisodes(SingleVideo singleVideos);

    Boolean deleteEpisode(String videoId);

    List<SingleVideo> getAllSingleVideo();

    List<SingleVideoResponse> addSingleVideoByBulkUpload(File csvFile);
}
