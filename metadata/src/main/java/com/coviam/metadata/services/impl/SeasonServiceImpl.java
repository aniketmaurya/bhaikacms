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

        try {
            return Optional.of(seasonRepository.save(season));

        }catch (Exception exception){

            log.error("exception "+ exception.getMessage()+" while adding season ");

            return Optional.empty();
        }

    }

    // todo use formatter in place of +
    // todo use boolean
    // todo use @Transactional for both episode delete and season delete
    @Override
    public Boolean deleteSeasonById(String seasonId) {

        try {
            episodeRepository.deleteAllBySeasonId(seasonId);
            seasonRepository.deleteById(seasonId);
        } catch (Exception exception) {
            log.error("exception "+ exception.getMessage()+" while deleting season by seasonId : "+seasonId);
            return false;
        }
        return true;
    }

    //TODO: remove try catch
    @Override
    public Optional<Season> getSeasonById(String seasonId) {
        try {

            return seasonRepository.findById(seasonId);

        } catch (Exception exception){

            log.error("exception : " +exception.getMessage()+ " while getting seasons by seasonId : "+ seasonId);

            return Optional.empty();

        }

    }

    @Override
    public Page<Season> getSeasonsByProgramId(String programId, Integer page, Integer size) {

        try {

            return seasonRepository.findByProgramId(programId, PageRequest.of(page,size));

        }catch (Exception exception){

            log.error("exception : " +exception.getMessage()+ " while getting seasons by programId : "+programId);

            return Page.empty();
        }

    }


}