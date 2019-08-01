package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.Season;
import com.coviam.metadata.repository.SeasonRepository;
import com.coviam.metadata.services.SeasonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeasonServiceImpl implements SeasonServices {

    @Autowired
    private SeasonRepository seasonRepository;

    @Override
    public Boolean addMultiCategory(Season season) {
        return false;
    }

    @Override
    public Boolean addSeason(Season season) {

        try {
            seasonRepository.save(season);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}