package com.coviam.metadata.services.impl;

import com.coviam.metadata.request.SeasonRequest;
import com.coviam.metadata.entity.Season;
import com.coviam.metadata.repository.EpisodeRepository;
import com.coviam.metadata.repository.SeasonRepository;
import com.coviam.metadata.response.SeasonResponse;
import com.coviam.metadata.services.SeasonServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
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
    public Boolean deleteBySeasonId(String seasonId) {


        try {
            episodeRepository.deleteBySeasonId(seasonId);
            seasonRepository.deleteById(seasonId);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public SeasonRequest getSeasonById(String seasonId) {

        Optional<Season> seasonOptional=seasonRepository.findById(seasonId);
        Season season=seasonOptional.get();
        SeasonRequest seasonRequest = new SeasonRequest();
        BeanUtils.copyProperties(season, seasonRequest);
        return seasonRequest;
    }

    @Override
    public List<SeasonResponse> getSeasonsByProgramId(String programId, Integer page, Integer size) {

        Page<Season> seasonPage=null;

        try {

            seasonPage=seasonRepository.findByProgramId(programId, PageRequest.of(page,size));

        }catch (Exception exception){

            log.error("exception : " +exception.getMessage()+ " while getting seasons by programId : "+programId);
        }

        List<SeasonResponse> seasonRequestList = new ArrayList<>();
        for (Season season:seasonPage) {
            SeasonResponse seasonResponse = SeasonResponse.builder().season(season).build();
            seasonRequestList.add(seasonResponse);
        }
        return seasonRequestList;
    }


}