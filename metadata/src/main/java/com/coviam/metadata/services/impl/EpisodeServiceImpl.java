package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.Episode;
import com.coviam.metadata.repository.EpisodeRepository;
import com.coviam.metadata.services.EpisodeServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EpisodeServiceImpl implements EpisodeServices {
    @Autowired
    private EpisodeRepository episodeRepository;


    // TODO CHANGE FROM EPISODREQUEST TO EPISODE
    @Override
    public List<Episode> addEpisodes(List<Episode> episodes) {
//        List<Episode> episodeList = new ArrayList<>();
//        try {
//
//            episodeRequests.forEach(episodeRequest -> {
//                Episode episode = new Episode();
//                BeanUtils.copyProperties(episodeRequest, episode);
//                episodeList.add(episode);
//            });
        episodeRepository.saveAll(episodes);
//        } catch (Exception e) {
//            log.error("Error while adding episode");
//        }

        return episodes;
    }


    @Override
    public Boolean deleteEpisode(String episodeId) {
        episodeRepository.deleteById(episodeId);
        log.debug("Deleted EpisodeId: {} ", episodeId);
        return Boolean.TRUE;
    }


    @Override
    public Page<Episode> getEpisodesBySeasonId(String seasonId, Integer pageNumber, Integer pageSize) {
        return episodeRepository.findBySeasonId(seasonId, PageRequest.of(pageNumber, pageSize));
    }
}
