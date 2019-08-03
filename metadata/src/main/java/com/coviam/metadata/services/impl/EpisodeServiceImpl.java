package com.coviam.metadata.services.impl;

import com.coviam.metadata.dto.request.AddAuditRequest;
import com.coviam.metadata.dto.response.AddAuditResponse;
import com.coviam.metadata.entity.Episode;
import com.coviam.metadata.repository.EpisodeRepository;
import com.coviam.metadata.services.EpisodeServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EpisodeServiceImpl implements EpisodeServices {
    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${audit.service.url}")
    private String auditServiceUrl;


    // TODO CHANGE FROM EPISODREQUEST TO EPISODE
    @Override
    public List<Episode> addEpisodes(List<Episode> episodes) {

        String userId="",modification="ADDED/UPDATED/DELETED";

//        List<Episode> episodeList = new ArrayList<>();
//        try {
//
//            episodeRequests.forEach(episodeRequest -> {
//                Episode episode = new Episode();
//                BeanUtils.copyProperties(episodeRequest, episode);
//                episodeList.add(episode);
//            });
        List<AddAuditRequest> auditRequests=new ArrayList<>();
        for (Episode episode: episodes) {
            AddAuditRequest auditRequest=AddAuditRequest.builder()
                    .actionBy(userId).actionName(modification.concat(" episodes"))
                    .actionTime(System.currentTimeMillis())
                    .contentId(episode.getId())
                    .contentName(episode.getEpisodeVideoUrl())
                    .newContent("Newly Added!")
                    .oldContent("-").build();
            auditRequests.add(auditRequest);

        }
        episodeRepository.saveAll(episodes);
//        } catch (Exception e) {
//            log.error("Error while adding episode");
//        }

        //TODO Audit
        Boolean response=restTemplate.postForObject(auditServiceUrl+"/audit/addAudit",auditRequests,Boolean.class);

        return episodes;
    }


    @Override
    public Boolean deleteEpisode(String episodeId) {
        episodeRepository.deleteById(episodeId);
        log.debug("Deleted EpisodeId: {} ", episodeId);
        //TODO Audit
        return Boolean.TRUE;
    }


    @Override
    public Page<Episode> getEpisodesBySeasonId(String seasonId, Integer pageNumber, Integer pageSize) {
        return episodeRepository.findBySeasonId(seasonId, PageRequest.of(pageNumber, pageSize));
    }
}
