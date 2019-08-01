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

import java.util.List;

@Service
public class EpisodeServiceImpl implements EpisodeServices {

    @Autowired
    EpisodeRepository episodeRepository;

    @Override
    public boolean addEpisodeVideo(EpisodeDto episodeDto){
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
    public int countEpisode(String seasonId) {
        int count = 0;
        List<Episode> episodeList = episodeRepository.findBySeasonId(seasonId);
        for (Episode episode:episodeList){
            count++;
        }
        return count;
    }

    @Override
    public boolean addEpisodeVideos(List<EpisodeDto> episodeDtoList) {
        for (EpisodeDto episodeDto:episodeDtoList) {
            Episode episode = new Episode();
            BeanUtils.copyProperties(episodeDto,episode);
            episodeRepository.save(episode);
        }
        return true;
    }

}
