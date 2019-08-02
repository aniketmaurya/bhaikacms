package com.coviam.metadata.services;

import com.coviam.metadata.request.SeasonRequest;
import com.coviam.metadata.entity.Season;
import com.coviam.metadata.response.SeasonResponse;

import java.util.List;

public interface SeasonServices {
    /**
     * addSeason and addMultiCategory are same just a difference of implementation and display
     **/

    Boolean addSeason(Season season);

    Integer countSeasonsByProgram(String programId);

    Boolean deleteBySeasonId(String seasonId);


    SeasonRequest getSeasonById(String seasonId);

    List<SeasonResponse> getSeasonsByProgramId(String programId, Integer page, Integer size);

}