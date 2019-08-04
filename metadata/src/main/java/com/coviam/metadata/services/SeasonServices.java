package com.coviam.metadata.services;

import com.coviam.metadata.dto.request.DeleteRequest;
import com.coviam.metadata.entity.Season;
import org.springframework.data.domain.Page;

public interface SeasonServices {
    /**
     * addSeason and addMultiCategory are same just a difference of implementation and display
     *
     * @param season
     * @return
     */

    Season addSeason(Season season);

    Boolean deleteSeasonById(DeleteRequest deleteRequest);

    Season getSeasonById(String seasonId);

    Page<Season> getSeasonsByProgramId(String programId, Integer page, Integer size);

    Page<Season> getAllSeasons(Integer pageNumber, Integer pageSize);

    Page<Season> getAllMultiVideo(Integer pageNumber, Integer pageSize);



}