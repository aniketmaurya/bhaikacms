package com.coviam.metadata.services.impl;

import com.coviam.metadata.dto.EpisodeDto;
import com.coviam.metadata.entity.Episode;
import com.coviam.metadata.entity.Season;
import com.coviam.metadata.repository.EpisodeRepository;
import com.coviam.metadata.services.EpisodeServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EpisodeServiceImpl implements EpisodeServices {

    @Autowired
    EpisodeRepository episodeRepository;

    @Override
    public boolean addSingleEpisodeVideo(EpisodeDto episodeDto){
            Episode episode = new Episode();
            BeanUtils.copyProperties(episodeDto,episode);
        try{
            episodeRepository.save(episode);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<EpisodeDto> getEpisodesBySeasonId(String seasonId) {
        List<Episode> episodesBySeasonIdList = episodeRepository.findBySeasonId(seasonId);
        List<EpisodeDto> episodesDtoBySeasonList = new ArrayList<EpisodeDto>();
        for(Episode episode:episodesBySeasonIdList){
            EpisodeDto episodeDto = new EpisodeDto();
            BeanUtils.copyProperties(episode,episodeDto);
            episodesDtoBySeasonList.add(episodeDto);
        }
        return episodesDtoBySeasonList;
    }

    @Override
    public boolean addMultipleEpisodeVideos(List<EpisodeDto> episodeDtoList) {
        for (EpisodeDto episodeDto:episodeDtoList) {
            Episode episode = new Episode();
            BeanUtils.copyProperties(episodeDto,episode);
            episodeRepository.save(episode);
        }
        return true;
    }

}
