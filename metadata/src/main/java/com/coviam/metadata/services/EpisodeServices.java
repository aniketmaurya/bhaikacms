package com.coviam.metadata.services;
import com.coviam.metadata.entity.Episode;
import com.coviam.metadata.request.EpisodeRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EpisodeServices {


    List<Episode> addEpisodes(List<EpisodeRequest> episodes);

    Boolean deleteEpisode(String episodeId);

    Page<Episode> getEpisodesBySeasonId(String seasonId, int pageNumber, int pageSize);

}
