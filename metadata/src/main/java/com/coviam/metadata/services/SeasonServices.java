package com.coviam.metadata.services;

import com.coviam.metadata.dto.request.DeleteRequest;
import com.coviam.metadata.dto.request.SeasonRequest;
import com.coviam.metadata.entity.Season;
import org.springframework.data.domain.Page;

import java.io.File;
import java.util.List;

public interface SeasonServices {
    /**
     * addSeason and addMultiCategory are same just a difference of implementation and display
     */

    Season addSeason(SeasonRequest seasonRequest);

    Boolean deleteSeasonById(DeleteRequest deleteRequest);

    Season getSeasonById(String seasonId);

    Page<Season> getSeasonsByProgramId(String programId, Integer pageNumber, Integer pageSize);

    Page<Season> getAllSeasons(Integer pageNumber, Integer pageSize);

    Page<Season> getAllMultiVideo(Integer pageNumber, Integer pageSize);

    Boolean editSeason(SeasonRequest seasonRequest);

    List<Season> addSeasonByBulkUpload(File csvFile);
}