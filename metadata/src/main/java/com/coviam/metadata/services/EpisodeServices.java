package com.coviam.metadata.services;

import com.coviam.metadata.dto.request.DeleteRequest;
import com.coviam.metadata.entity.Episode;
import org.springframework.data.domain.Page;

import java.io.File;
import java.util.List;

public interface EpisodeServices {


    List<Episode> addEpisodes(List<Episode> episodes);

    Boolean deleteEpisode(DeleteRequest deleteRequest);

    Page<Episode> getEpisodesBySeasonId(String seasonId, Integer pageNumber, Integer pageSize);

    List<Episode> addEpisodeByBulkUpload(File csvFile);

}
