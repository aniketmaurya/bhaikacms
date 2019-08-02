package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.Episode;
import com.coviam.metadata.repository.EpisodeRepository;
import com.coviam.metadata.services.EpisodeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeServiceImpl implements EpisodeServices {

    @Autowired
    private EpisodeRepository episodeRepository;

    @Override
    public Boolean addEpisodes(List<Episode> episodes) {
        try {
            episodeRepository.saveAll(episodes);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Deprecated
    @Override
    public Integer countEpisodesBySeasonId(String seasonId) {

        return episodeRepository.countBySeasonId(seasonId);
    }

    @Override
    public Boolean deleteEpisode(String episodeId) {
        try {
            episodeRepository.deleteById(episodeId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
