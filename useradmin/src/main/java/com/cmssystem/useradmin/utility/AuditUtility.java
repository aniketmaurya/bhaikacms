package com.cmssystem.useradmin.utility;

import com.cmssystem.useradmin.dto.AuditRequestDto;
import com.cmssystem.useradmin.dto.Change;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Slf4j
public class AuditUtility {


    public void callAudit(AuditRequestDto auditRequest) {
        RestTemplate restTemplate = new RestTemplate();
        String auditServiceUrl = "http://172.16.20.83:8082";
        Boolean response = restTemplate.postForObject(auditServiceUrl + "/audit/addAudit", auditRequest, Boolean.class);
    }

    public void addAudit(String userName, String userEmail, String modifierEmail, List<Change> changes) {

        AuditRequestDto auditRequestDto = AuditRequestDto.builder()
                .asset(userName)
                .assetId(userEmail)
                .action("ADDED")
                .modifier(modifierEmail)
                .changes(changes).build();

        callAudit(auditRequestDto);
    }

    public void deleteAudit(String userName, String userEmail, String modifierEmail, List<Change> changes) {

        AuditRequestDto auditRequestDto = AuditRequestDto.builder()
                .asset(userName)
                .assetId(userEmail)
                .action("DELETED")
                .modifier(modifierEmail)
                .changes(changes).build();

        callAudit(auditRequestDto);
    }


    public void editAudit(String userName, String userEmail, String modifierEmail, List<Change> changes) {
        AuditRequestDto auditRequestDto = AuditRequestDto.builder()
                .asset(userName)
                .assetId(userEmail)
                .action("EDITED")
                .modifier(modifierEmail)
                .changes(changes).build();

        callAudit(auditRequestDto);

    }
}