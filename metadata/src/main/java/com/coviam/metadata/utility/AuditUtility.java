package com.coviam.metadata.utility;

import com.coviam.metadata.dto.request.AuditRequest;
import com.coviam.metadata.entity.Episode;
import com.coviam.metadata.entity.Program;
import com.coviam.metadata.entity.Season;
import com.coviam.metadata.entity.SingleVideo;
import org.springframework.web.client.RestTemplate;

public class AuditUtility {

    public static void callAudit(AuditRequest auditRequest) {

        RestTemplate restTemplate = new RestTemplate();
        String auditServiceUrl = "http://172.16.20.83:8082";

        Boolean response = restTemplate.postForObject(auditServiceUrl + "/audit/addAudit", auditRequest, Boolean.class);

    }

    public static void programAudit(Program oldProgram, Program newProgram, String actionName) {

        // comparing difference of each object

        AuditRequest auditRequest = AuditRequest.builder()
                .newContent(newProgram.toString())
                .oldContent(oldProgram.toString())
                .contentType("Program")
                .actionName(actionName)
                .build();

        callAudit(auditRequest);

    }

    public static void seasonAudit(Season oldSeason, Season newSeason, String actionName) {

        AuditRequest auditRequest = AuditRequest.builder()
                .newContent(newSeason.toString())
                .oldContent(oldSeason.toString())
                .contentType("Season")
                .actionName(actionName)
                .build();

        callAudit(auditRequest);

    }

    public static void episodeAudit(Episode oldEpisode, Episode newEpisode, String actionName) {

        AuditRequest auditRequest = AuditRequest.builder()
                .newContent(newEpisode.toString())
                .oldContent(oldEpisode.toString())
                .contentType("Episode")
                .contentId(newEpisode.getId())
                .actionName(actionName)
                .build();

        callAudit(auditRequest);

    }

    public static void singleVideoAudit(SingleVideo newSingleVideo, SingleVideo oldSingleVideo, String actionName) {

        AuditRequest auditRequest = AuditRequest.builder()
                .newContent(newSingleVideo.toString())
                .oldContent(oldSingleVideo.toString())
                .contentType("Single Video Category")
                .actionName(actionName)
                .build();

        callAudit(auditRequest);

    }
}