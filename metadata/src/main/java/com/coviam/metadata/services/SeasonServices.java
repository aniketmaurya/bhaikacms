package com.coviam.metadata.services;

import com.coviam.metadata.entity.Season;

public interface SeasonServices {
    /**
     * addSeason and addMultiCategory are same just a difference of implementation and display
     **/
    boolean addSeason(Season season);

    boolean addMultiCategory(Season season);

}
