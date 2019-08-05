package com.cmssystem.audit.controller;

import com.cmssystem.audit.dto.AddAuditRequestDto;
import com.cmssystem.audit.dto.AuditFilterDto;
import com.cmssystem.audit.dto.GetAuditResponseDto;
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
     * @param addAuditRequestDto entry for audit table
     * @return boolean added,string message
     */
    @RequestMapping(method = RequestMethod.POST, value = "/addAudit")
    public ResponseEntity<Boolean> addAudit(@RequestBody AddAuditRequestDto addAuditRequestDto) {
        return ResponseEntity.ok(auditService.addAudit(addAuditRequestDto));
    }

    /**
     * @param auditFilterDto pagenumber and count in page and other filters
     * @return auditdto audit data to be displayed
     */
    @RequestMapping(method = RequestMethod.POST, value = "/getAudits")
    public ResponseEntity<Page<GetAuditResponseDto>> getAudits(@RequestBody AuditFilterDto auditFilterDto) {
        return ResponseEntity.ok(auditService.getAudits(auditFilterDto));
    }

    /**
     * @param contentId pagenumber and count in page and other filters
     * @return auditdto audit data to be displayed
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getRecent")
    public ResponseEntity<String> getRecentModifier(@RequestParam(value = "contentId") String contentId) {
        return ResponseEntity.ok(auditService.getRecentModifier(contentId));
    }

}
