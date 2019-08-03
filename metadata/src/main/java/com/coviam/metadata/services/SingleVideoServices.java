package com.coviam.metadata.services;

import com.coviam.metadata.entity.SingleVideo;

public interface SingleVideoServices {

    SingleVideo addEpisodes(SingleVideo singleVideos);

    Boolean deleteEpisode(String videoId);
}
