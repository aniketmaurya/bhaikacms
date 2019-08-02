package com.coviam.metadata.services.impl;

import com.coviam.metadata.entity.Episode;
import com.coviam.metadata.repository.EpisodeRepository;
import com.coviam.metadata.request.EpisodeRequest;
import com.coviam.metadata.services.EpisodeServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EpisodeServiceImpl implements EpisodeServices {
    @Autowired
    private EpisodeRepository episodeRepository;


    @Override
    public List<Episode> addEpisodes(List<EpisodeRequest> episodeRequests) {
        List<Episode> episodeList = new ArrayList<>();
        try {

            episodeRequests.forEach(episodeRequest -> {
                Episode episode = new Episode();
                BeanUtils.copyProperties(episodeRequest, episode);
                episodeList.add(episode);
            });
            episodeRepository.saveAll(episodeList);
        } catch (Exception e) {
            log.debug("Error while adding episode");
        }

        return episodeList;
    }


    @Override
    public Boolean deleteEpisode(String episodeId) {
        try {
            episodeRepository.deleteById(episodeId);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("Error deleting in episode with Episode Id:{}", episodeId);
            return false;
        }
        return true;
    }


    @Override
    public Page<Episode> getEpisodesBySeasonId(String seasonId, int pageNumber, int pageSize) {
        return episodeRepository.findBySeasonId(seasonId, PageRequest.of(pageNumber, pageSize));
    }
}
