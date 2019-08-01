package com.coviam.metadata.services;

import com.coviam.metadata.entity.Season;

import java.util.List;

public interface SeasonServices {
    /**
     * addSeason and addMultiCategory are same just a difference of implementation and display
     **/

    boolean addMultiCategory(Season season);

    List<Season> addSeason(Season season);

}
