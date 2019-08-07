package com.coviam.metadata.services.impl;

import com.coviam.metadata.dto.request.DeleteRequest;
import com.coviam.metadata.dto.response.EpisodeResponse;
import com.coviam.metadata.entity.Episode;
import com.coviam.metadata.entity.Program;
import com.coviam.metadata.repository.EpisodeRepository;
import com.coviam.metadata.services.EpisodeServices;
import com.coviam.metadata.services.ProgramServices;
import com.coviam.metadata.utility.SearchUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EpisodeServiceImpl implements EpisodeServices {
    @Autowired
    private EpisodeRepository episodeRepository;
    @Autowired
    private SearchUtility searchUtility;
    @Autowired
    private ProgramServices programServices;

    // TODO CHANGE FROM EPISODREQUEST TO EPISODE
    @Override
    public List<Episode> addEpisodes(List<Episode> episodes) {

        episodes.forEach(episode -> episode.setCreationDate(System.currentTimeMillis()));
        String userId = "", modification = "ADDED/UPDATED/DELETED";
        List<Episode> episodes1 = new ArrayList<>();
        episodeRepository.saveAll(episodes).forEach(episodes1::add);
        for (Episode episode : episodes1) {
            Program program = programServices.getProgramById(episode.getSeason().getProgram().getId());
            episode.getSeason().setProgram(program);
        }
        episodes1 = episodes1.stream().filter(Objects::nonNull).collect(Collectors.toList());
        log.info("Adding episodes");
        searchUtility.addEpisodesToSearch(new EpisodeResponse(episodes1));
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
