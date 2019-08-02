package com.coviam.metadata.services;

import com.coviam.metadata.entity.Season;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface SeasonServices {
    /**
     * addSeason and addMultiCategory are same just a difference of implementation and display
     *
     * @param season
     * @return*/

    Optional<Season> addSeason(Season season);

    Boolean deleteSeasonById(String seasonId);

    Optional<Season> getSeasonById(String seasonId);

    Page<Season> getSeasonsByProgramId(String programId, Integer page, Integer size);

}