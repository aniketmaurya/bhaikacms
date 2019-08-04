package com.cmssystem.useradmin.utility;

import com.cmssystem.useradmin.dto.AuditRequestDto;
import com.cmssystem.useradmin.entity.UserAdmin;
import com.cmssystem.useradmin.repository.UserAdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Slf4j
public class AuditUtility {


    public void callAudit(AuditRequestDto auditRequest) {
        RestTemplate restTemplate = new RestTemplate();
        String auditServiceUrl = "http://172.16.20.83:8082";
        Boolean response = restTemplate.postForObject(auditServiceUrl + "/audit/addAudit", auditRequest, Boolean.class);
    }

    public void addAudit(String userId,String userName) {

           log.warn("Id is : {} and name: {}", userId,userName);


            AuditRequestDto auditRequestDto = AuditRequestDto.builder()
                    .contentId(userId)
                    .contentName("User Added")
                    .newContent("User Added")
                    .actionName(userName)
                    .actionTime(System.currentTimeMillis())
                    .actionBy(userName).build();

            callAudit(auditRequestDto);
    }

    public void deleteAudit(String userId,String userId1,String userName,String userName1) {

        AuditRequestDto auditRequestDto = AuditRequestDto.builder()
                .contentId(userId1)
                .contentName("User Deleted")
                .newContent("User Deleted")
                .actionName(userName +" got deleted")
                .actionTime(System.currentTimeMillis())
                .actionBy(userName1).build();

        callAudit(auditRequestDto);
    }


}