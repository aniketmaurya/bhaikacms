package com.coviam.metadata.services;

import com.coviam.metadata.dto.EpisodeDto;

import java.util.List;

public interface EpisodeServices {

    boolean addSingleEpisodeVideo(EpisodeDto episodeDto);

    boolean addMultipleEpisodeVideos(List<EpisodeDto> episodeDtoList);

    List<EpisodeDto> getEpisodesBySeasonId(String seasonId);
}
