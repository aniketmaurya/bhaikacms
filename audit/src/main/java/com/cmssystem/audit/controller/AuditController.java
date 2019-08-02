package com.cmssystem.audit.controller;

import com.cmssystem.audit.dto.AddAuditResponseDto;
import com.cmssystem.audit.dto.AuditDto;
import com.cmssystem.audit.dto.AuditRequestDto;
import com.cmssystem.audit.services.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/audit")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class AuditController {

    @Autowired
    private AuditService auditService;

    /**
     * @param auditDto entry for audit table
     * @return boolean added,string message
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addAudit")
    public ResponseEntity<AddAuditResponseDto> addAudit(@RequestBody AuditDto auditDto) {
        AddAuditResponseDto response = auditService.addAudit(auditDto);
        return ResponseEntity.ok(response);
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
