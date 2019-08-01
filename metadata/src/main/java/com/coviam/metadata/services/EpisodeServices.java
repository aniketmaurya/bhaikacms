package com.coviam.metadata.services;

import com.coviam.metadata.dto.EpisodeDto;
import com.coviam.metadata.entity.Episode;
import com.coviam.metadata.entity.Season;

import java.util.List;

public interface EpisodeServices {

    boolean addSingleEpisodeVideo(EpisodeDto episodeDto);
    boolean addMultipleEpisodeVideos(List<EpisodeDto> episodeDtoList);
    List<Episode> getEpisodesBySeasonId(String seasonId);
}
