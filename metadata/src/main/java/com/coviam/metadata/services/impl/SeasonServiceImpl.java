package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.Season;
import com.coviam.metadata.repository.EpisodeRepository;
import com.coviam.metadata.repository.SeasonRepository;
import com.coviam.metadata.services.SeasonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeasonServiceImpl implements SeasonServices {

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

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

    @Deprecated
    @Override
    public Integer countSeasonsByProgram(String programId) {


        return seasonRepository.countByProgramId(programId);
    }

    @Override
    public Boolean deleteSeasonById(String seasonId) {
        try {
            episodeRepository.deleteBySeasonId(seasonId);
            seasonRepository.deleteById(seasonId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}