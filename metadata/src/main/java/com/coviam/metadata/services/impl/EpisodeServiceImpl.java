package com.coviam.metadata.services.impl;

import com.coviam.metadata.dto.request.DeleteRequest;
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

        episodes.forEach(episode -> episode.setCreationDate(System.currentTimeMillis()));
        episodeRepository.saveAll(episodes);
        log.info("Adding episodes");
        return episodes;
    }

    @Override
    public Boolean deleteEpisode(DeleteRequest deleteRequest) {
        episodeRepository.deleteById(deleteRequest.getId());
        log.debug("Deleted EpisodeId: {} ", deleteRequest.getId());
        //TODO Audit
//        AuditUtility.deleteAudit("", "",
//                deleteRequest.getUserId(), "EPISODE");
        return Boolean.TRUE;
    }

    @Override
    public Page<Episode> getEpisodesBySeasonId(String seasonId, Integer pageNumber, Integer pageSize) {
        return episodeRepository.findBySeasonId(seasonId, PageRequest.of(pageNumber, pageSize));
    }
}
