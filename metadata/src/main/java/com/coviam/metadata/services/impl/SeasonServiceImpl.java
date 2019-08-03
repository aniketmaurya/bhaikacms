package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.Season;
import com.coviam.metadata.repository.EpisodeRepository;
import com.coviam.metadata.repository.SeasonRepository;
import com.coviam.metadata.services.SeasonServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class SeasonServiceImpl implements SeasonServices {

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private EpisodeRepository episodeRepository;


    @Override
    public Optional<Season> addSeason(Season season) {
        return Optional.of(seasonRepository.save(season));
    }

    @Transactional
    @Override
    public Boolean deleteSeasonById(String seasonId) {
        episodeRepository.deleteAllBySeasonId(seasonId);
        seasonRepository.deleteById(seasonId);
        return Boolean.TRUE;
    }

    @Override
    public Optional<Season> getSeasonById(String seasonId) {
        return seasonRepository.findById(seasonId);
    }

    // todo change the log error
    @Override
    public Page<Season> getSeasonsByProgramId(String programId, Integer page, Integer size) {
        return seasonRepository.findByProgramId(programId, PageRequest.of(page, size));
        /*try {
            return seasonRepository.findByProgramId(programId, PageRequest.of(page, size));
        } catch (Exception exception) {
            log.error("exception : {} while getting seasons by programId : {}",
                    exception.getMessage(), programId);
            return Page.empty();
        }*/
    }


}