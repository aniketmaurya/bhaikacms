package com.coviam.metadata.services.impl;

import com.coviam.metadata.dto.EpisodeDto;
import com.coviam.metadata.entity.Episode;
import com.coviam.metadata.repository.EpisodeRepository;
import com.coviam.metadata.services.EpisodeServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    @Override
    public List<EpisodeDto> getEpisodesBySeasonId(String seasonId, int pageNumber, int pageSize) {

        Page<Episode> episodesBySeasonId = episodeRepository.findBySeasonId(seasonId, PageRequest.of(pageNumber, pageSize));
        List<EpisodeDto> episodesDtoList = new ArrayList<>();
        for (Episode episode :
                episodesBySeasonId) {
            EpisodeDto episodeDto = new EpisodeDto();
            BeanUtils.copyProperties(episode, episodeDto);
            episodesDtoList.add(episodeDto);
        }
        return episodesDtoList;
    }
}
