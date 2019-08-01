package com.coviam.metadata.services;

import com.coviam.metadata.dto.EpisodeDto;
import com.coviam.metadata.entity.Episode;
import com.coviam.metadata.entity.Season;

import java.util.List;

public interface EpisodeServices {

    boolean addEpisodeVideo(EpisodeDto episodeDto);
    int countEpisode(String seasonId);
    boolean addEpisodeVideos(List<EpisodeDto> episodeDtoList);
    List<Episode> findEpisodesBySeasonId
}
