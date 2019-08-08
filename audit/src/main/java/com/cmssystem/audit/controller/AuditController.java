package com.cmssystem.audit.controller;

import com.cmssystem.audit.dto.AddAuditRequestDto;
import com.cmssystem.audit.dto.AuditFilterDto;
import com.cmssystem.audit.dto.AuditResponseDto;
import com.cmssystem.audit.services.AuditService;
import com.cmssystem.audit.utilities.GeneratePdfReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

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
    public ResponseEntity<Page<AuditResponseDto>> getAudits(@RequestBody AuditFilterDto auditFilterDto) {
        return ResponseEntity.ok(auditService.getAudits(auditFilterDto));
    }

    /**
     * @param contentId id of content
     * @return string emailid
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getRecent")
    public ResponseEntity<String> getRecentModifier(@RequestParam(value = "contentId") String contentId) {
        log.warn("CONTENT CONTROLLER:: {}", contentId);
        return ResponseEntity.ok(auditService.getRecentModifier(contentId));
    }

    @RequestMapping(value = "/getReport", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> auditReport(@RequestParam(value = "start") Long start,
                                                           @RequestParam(value = "end") Long end) {

        log.warn("REPORT AT CONTROLLER: {} {}", start, end);

        List<AuditResponseDto> audits = auditService.getAuditsToExport(start, end);

        ByteArrayInputStream bis = GeneratePdfReport.pdfReport(audits);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}
