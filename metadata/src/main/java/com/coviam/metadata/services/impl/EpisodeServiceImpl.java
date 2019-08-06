package com.coviam.metadata.services.impl;

import com.coviam.metadata.dto.request.DeleteRequest;
import com.coviam.metadata.entity.Episode;
import com.coviam.metadata.repository.EpisodeRepository;
import com.coviam.metadata.repository.SeasonRepository;
import com.coviam.metadata.services.EpisodeServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class EpisodeServiceImpl implements EpisodeServices {
    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private SeasonRepository seasonRepository;

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

    @Override
    public List<Episode> addEpisodeByBulkUpload(File csvFile) {
        String line = "";
        String csvSplitBy = ",";
        List<Episode> episodeList = new ArrayList<>();
        try {
            FileReader file = new FileReader(csvFile);
            BufferedReader br = new BufferedReader(file);

            line = br.readLine();
            String[] headers = line.split(csvSplitBy);
            if (headers[0].equalsIgnoreCase("Episode Number") && headers[1].equalsIgnoreCase("Episode Title")
                    && headers[2].equalsIgnoreCase("Episode Description") && headers[3].equalsIgnoreCase("episode Video URL")
                    && headers[4].equalsIgnoreCase("Thumbnail Image URL") && headers[5].equalsIgnoreCase("Avatar Image URL")
                    && headers[6].equalsIgnoreCase("Season Id")) {
                while ((line = br.readLine()) != null) {
                    String[] records = line.split(csvSplitBy);

                    seasonRepository.findById(records[6]).ifPresent(season -> {
                        HashMap<String, String> images = new HashMap<>();
                        images.put("Thumbnail", records[4]);
                        images.put("Avatar", records[5]);
                        Episode episode = Episode.builder()
                                .season(season)
                                .episodeNumber(Integer.parseInt(records[0]))
                                .episodeTitle(records[1])
                                .episodeDescription(records[2])
                                .episodeVideoUrl(records[3])
                                .episodeImgUrls(images)
                                .crewList(images).build();

                        log.info("Added episode with Episode Number : {}", episode.getEpisodeNumber());
                        episodeList.add(episode);
                    });
                }
            }
        } catch (Exception e) {
            log.debug("Error while uploading episode: {}" + e.getMessage());
        }
        List<Episode> episodeList1 = addEpisodes(episodeList);
        System.out.println(episodeList1);
        return episodeList1;
    }
}
