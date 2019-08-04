package com.cmssystem.audit.controller;

import com.cmssystem.audit.dto.AuditDto;
import com.cmssystem.audit.dto.AuditRequestDto;
import com.cmssystem.audit.services.AuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/audit")
@CrossOrigin(allowedHeaders = "*", origins = "*")
@Slf4j
public class AuditController {

    @Autowired
    private AuditService auditService;

    /**
     * @param auditDto entry for audit table
     * @return boolean added,string message
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addAudit")
    public ResponseEntity<Boolean> addAudit(@RequestBody AuditDto auditDto) {

        return ResponseEntity.ok(auditService.addAudits(auditDto));
    }

    /**
     * @param auditRequestDto pagenumber and count in page
     * @return auditdto audit data to be displayed
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getAudits")
    public ResponseEntity<Page<AuditDto>> getAllAudits(@RequestBody AuditRequestDto auditRequestDto) {
        return ResponseEntity.ok(auditService.getAudits(auditRequestDto));
    }

}
