package com.coviam.metadata.services;

import com.coviam.metadata.entity.SingleVideo;

import java.util.List;

public interface SingleVideoServices {

    SingleVideo addEpisodes(SingleVideo singleVideos);

    Boolean deleteEpisode(String videoId);

    List<SingleVideo> getAllSingleVideo();
}
