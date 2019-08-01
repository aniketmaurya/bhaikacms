package com.coviam.metadata.services;

import com.coviam.metadata.entity.Episode;

public interface EpisodeServices {
    boolean addSingleVideo(Episode episode);

    boolean addEpisodeVideo(Episode episode);


}
