package com.coviam.metadata.services;

import com.coviam.metadata.entity.Episode;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EpisodeServices {


    List<Episode> addEpisodes(List<Episode> episodes);

    Boolean deleteEpisode(String episodeId);

    Page<Episode> getEpisodesBySeasonId(String seasonId, Integer pageNumber, Integer pageSize);

}
