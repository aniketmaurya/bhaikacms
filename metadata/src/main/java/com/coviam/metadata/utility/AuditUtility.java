package com.coviam.metadata.utility;

import com.coviam.metadata.dto.request.Change;
import com.coviam.metadata.dto.response.AuditResponse;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AuditUtility {

    @Async
    public void callAudit(AuditResponse auditResponse) {

        RestTemplate restTemplate = new RestTemplate();
        String auditServiceUrl = "http://172.16.20.83:8082";

        Boolean response = restTemplate.postForObject(auditServiceUrl + "/audit/addAudit", auditResponse, Boolean.class);


    }

    @Async
    public void addAudit(String assetId,
                         String asset,
                         String userEmail,
                         List<Change> changes) {


        AuditResponse auditResponse = AuditResponse.builder()
                .action("ADDED")
                .asset(asset)
                .assetId(assetId)
                .modifier(userEmail)
                .changes(changes)
                .build();

        callAudit(auditResponse);


    }

    @Async
    public void editAudit(String assetId,
                          String asset,
                          String userEmail,
                          List<Change> changes) {

        AuditResponse auditResponse = AuditResponse.builder()
                .action("EDITED")
                .asset(asset)
                .assetId(assetId)
                .modifier(userEmail)
                .changes(changes)
                .build();

        callAudit(auditResponse);

    }

    public void deleteAudit(String assetId,
                            String asset,
                            String userEmail,
                            List<Change> changes) {

        AuditResponse auditResponse = AuditResponse.builder()
                .action("DELETED")
                .asset(asset)
                .assetId(assetId)
                .modifier(userEmail)
                .changes(changes)
                .build();

        callAudit(auditResponse);
    }

}

