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
    public Season addSeason(Season season) {
        return Optional.of(seasonRepository.save(season)).orElse(new Season());
    }

    @Transactional
    @Override
    public Boolean deleteSeasonById(String seasonId) {
        episodeRepository.deleteAllBySeasonId(seasonId);
        seasonRepository.deleteById(seasonId);
        return Boolean.TRUE;
    }

    @Override
    public Season getSeasonById(String seasonId) {
        return seasonRepository.findById(seasonId).orElse(new Season());
    }

    // todo change the log error
    @Override
    public Page<Season> getSeasonsByProgramId(String programId, Integer page, Integer size) {
        return seasonRepository.findByProgramId(programId, PageRequest.of(page, size));
    }

    @Override
    public Page<Season> getAllSeasons(Integer pageNumber, Integer pageSize) {
        return seasonRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

}