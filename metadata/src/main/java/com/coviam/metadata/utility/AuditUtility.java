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

    public static void addAudit(String oldContent, String newContent,
                                String actionBy,
                                String contentType) {

        AuditRequest auditRequest = AuditRequest.builder()
                .newContent(newContent)
                .oldContent(oldContent)
                .contentType(contentType)
                .actionBy(actionBy)
                .actionName("ADD")
                .build();

        callAudit(auditRequest);
    }

    public static void editAudit(String oldContent, String newContent,
                                 String actionBy,
                                 String contentType) {

        AuditRequest auditRequest = AuditRequest.builder()
                .newContent(newContent)
                .oldContent(oldContent)
                .contentType(contentType)
                .actionBy(actionBy)
                .actionName("EDIT")
                .actionTime(System.currentTimeMillis())
                .build();
        callAudit(auditRequest);


    }

    public static void deleteAudit(String oldContent, String newContent,
                                   String actionBy,
                                   String contentType) {

        AuditRequest auditRequest = AuditRequest.builder()
                .newContent(newContent)
                .oldContent(oldContent)
                .contentType(contentType)
                .actionBy(actionBy)
                .actionName("DELETE")
                .actionTime(System.currentTimeMillis())
                .build();
        callAudit(auditRequest);
    }

    public static void programAudit(Program oldProgram, Program newProgram,
                                    String actionName, String actionBy) {


        AuditRequest auditRequest = AuditRequest.builder()
                .newContent(newProgram.toString())
                .oldContent(oldProgram.toString())
                .contentType("Program")
                .actionBy(actionBy)
                .actionName(actionName)
                .actionTime(System.currentTimeMillis())
                .build();

        callAudit(auditRequest);

    }

    public static void seasonAudit(Season oldSeason, Season newSeason,
                                   String actionName, String actionBy) {

        AuditRequest auditRequest = AuditRequest.builder()
                .newContent(newSeason.toString())
                .oldContent(oldSeason.toString())
                .contentType("Season")
                .actionName(actionName)
                .actionBy(actionBy)
                .build();

        callAudit(auditRequest);

    }

    public static void episodeAudit(Episode oldEpisode, Episode newEpisode,
                                    String actionName, String actionBy) {

        AuditRequest auditRequest = AuditRequest.builder()
                .newContent(newEpisode.toString())
                .oldContent(oldEpisode.toString())
                .contentType("Episode")
                .contentId(newEpisode.getId())
                .actionName(actionName)
                .actionBy(actionBy)
                .build();

        callAudit(auditRequest);
    }

    public static void singleVideoAudit(SingleVideo newSingleVideo, SingleVideo oldSingleVideo,
                                        String actionName, String actionBy) {
        AuditRequest auditRequest = AuditRequest.builder()
                .newContent(newSingleVideo.toString())
                .oldContent(oldSingleVideo.toString())
                .contentType("Single Video Category")
                .actionName(actionName)
                .actionBy(actionBy)
                .build();

        callAudit(auditRequest);

    }
}

