package com.coviam.metadata.services;

import com.coviam.metadata.entity.Episode;

import java.util.List;

public interface EpisodeServices {

    Boolean addEpisode(Episode episode);

    Boolean addMultipleEpisodes(List<Episode> episodes);

    Integer countEpisodesBySeasonId(String seasonId);

    Boolean deleteEpisode(String episodeId);

    Boolean deleteAllEpisodes();

}
