package com.coviam.metadata.services.impl;

import com.coviam.metadata.dto.request.Change;
import com.coviam.metadata.dto.request.DeleteRequest;
import com.coviam.metadata.dto.request.SeasonRequest;
import com.coviam.metadata.dto.response.SeasonResponse;
import com.coviam.metadata.entity.Season;
import com.coviam.metadata.repository.EpisodeRepository;
import com.coviam.metadata.repository.ProgramRepository;
import com.coviam.metadata.repository.SeasonRepository;
import com.coviam.metadata.services.SeasonServices;
import com.coviam.metadata.utility.AuditUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SeasonServiceImpl implements SeasonServices {

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private EpisodeRepository episodeRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private AuditUtility auditUtility;


    @Override
    public Season addSeason(SeasonRequest seasonRequest) {

        Season season = new Season();
        BeanUtils.copyProperties(seasonRequest, season);

        season.setCreationDate(System.currentTimeMillis());

        Season season1 = Optional.of(seasonRepository.save(season)).orElse(new Season());

        auditUtility.addAudit(season1.getId(),
                season1.getSeasonName(),
                seasonRequest.getUserEmail(),
                new ArrayList<Change>());

        return season1;
    }

    @Transactional
    @Override
    public Boolean deleteSeasonById(DeleteRequest deleteRequest) {

        Season season = seasonRepository.findById(deleteRequest.getId()).orElse(new Season());
        seasonRepository.deleteById(deleteRequest.getId());

        auditUtility.deleteAudit(deleteRequest.getId(),
                season.getSeasonName(),
                deleteRequest.getUserEmail(),
                new ArrayList<Change>());
        return Boolean.TRUE;
    }

    @Override
    public Season getSeasonById(String seasonId) {
        return seasonRepository.findById(seasonId).orElse(new Season());
    }

    // todo change the log error
    @Override
    public Page<Season> getSeasonsByProgramId(String programId, Integer pageNumber, Integer size) {
        return seasonRepository.findByProgramId(programId, PageRequest.of(pageNumber, size));
    }

    @Override
    public Page<Season> getAllSeasons(Integer pageNumber, Integer pageSize) {
        return seasonRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Page<Season> getAllMultiVideo(Integer pageNumber, Integer pageSize) {
        return seasonRepository.findBySeasonNumber(0, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public Boolean editSeason(SeasonRequest seasonRequest) {
        Season season = seasonRepository.findById(seasonRequest.getId()).orElse(new Season());

        List<Change> changes = new ArrayList<>();
        String fieldChanged = "";
        String oldValue = "";
        String newValue = "";
        if (!season.getSeasonName().equals(seasonRequest.getSeasonName())) {
            fieldChanged = "Name";
            oldValue = season.getSeasonName();
            newValue = seasonRequest.getSeasonName();
            changes.add(new Change(fieldChanged, oldValue, newValue));
        }
        if (!season.getSeasonDescription().equals(seasonRequest.getSeasonDescription())) {
            fieldChanged = "Description";
            oldValue = season.getSeasonName();
            newValue = seasonRequest.getSeasonName();
            changes.add(new Change(fieldChanged, oldValue, newValue));
        }

        BeanUtils.copyProperties(seasonRequest, season);
        seasonRepository.save(season);
        log.info("Season update seasonId: {} ", seasonRequest.getId());
        auditUtility.editAudit(season.getId(),
                season.getSeasonName(),
                seasonRequest.getUserEmail(),
                changes);

        return Boolean.TRUE;
    }


    @Override
    public List<SeasonResponse> addSeasonByBulkUpload(File csvFile) {

        String line = "";
        String csvSplitBy = ",";
        List<SeasonResponse> seasonResponseList = new ArrayList<>();
        try {
            FileReader file = new FileReader(csvFile);
            BufferedReader br = new BufferedReader(file);
            line = br.readLine();
            String[] headers = line.split(csvSplitBy);

            if (headers[0].equalsIgnoreCase("Program Id") && headers[1].equalsIgnoreCase("Season Name")
                    && headers[2].equalsIgnoreCase("Season Number") && headers[3].equalsIgnoreCase("Season Description")
                    && headers[4].equalsIgnoreCase("Thumbnail Image Url") && headers[5].equalsIgnoreCase("Avatar Image Url")
                    && headers[6].equalsIgnoreCase("User Id") && headers[7].equalsIgnoreCase("Email Id")) {

                while ((line = br.readLine()) != null) {
                    String[] records = line.split(csvSplitBy);
                    programRepository.findById(records[0]).ifPresent(program -> {

                        HashMap<String, String> images = new HashMap<>();
                        images.put("Thumbnail", records[4]);
                        images.put("Avatar", records[5]);

                        SeasonRequest seasonRequest = SeasonRequest.builder()
                                .program(program)
                                .seasonName(records[1])
                                .seasonNumber(Integer.parseInt((records[2])))
                                .seasonDescription(records[3])
                                .seasonImgUrls(images)
                                .userId(records[6])
                                .userEmail(records[7])
                                .build();

                        Season season = addSeason(seasonRequest);
                        SeasonResponse seasonResponse = new SeasonResponse();
                        if (season == null) {
                            seasonResponse.setSeasonRequest(seasonRequest);
                            seasonResponse.setIsSuccessful(false);
                        }
                        log.info("Added season with season id:{}", season.getId());
                        seasonResponseList.add(seasonResponse);
                    });
                }
            }
        } catch (Exception e) {
            log.debug("Error while uploading season:{}", e.getMessage());
        }
        return seasonResponseList;
    }

}