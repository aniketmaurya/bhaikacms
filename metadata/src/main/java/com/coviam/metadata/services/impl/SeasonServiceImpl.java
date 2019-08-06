package com.coviam.metadata.services.impl;

import com.coviam.metadata.dto.request.Change;
import com.coviam.metadata.dto.request.DeleteRequest;
import com.coviam.metadata.dto.request.SeasonRequest;
import com.coviam.metadata.entity.Season;
import com.coviam.metadata.repository.EpisodeRepository;
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

import java.util.ArrayList;
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

}