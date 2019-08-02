package com.coviam.metadata.services;

import com.coviam.metadata.dto.EpisodeDto;
import com.coviam.metadata.entity.Episode;

import java.util.List;

public interface EpisodeServices {


    Boolean addEpisodes(List<Episode> episodes);

    Integer countEpisodesBySeasonId(String seasonId);

    Boolean deleteEpisode(String episodeId);

    List<EpisodeDto> getEpisodesBySeasonId(String seasonId, int pageNumber, int pageSize);

}
