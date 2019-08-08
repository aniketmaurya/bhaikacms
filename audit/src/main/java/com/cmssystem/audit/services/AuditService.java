package com.cmssystem.audit.services;

import com.cmssystem.audit.dto.AddAuditRequestDto;
import com.cmssystem.audit.dto.AuditFilterDto;
import com.cmssystem.audit.dto.AuditResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AuditService {

    Boolean addAudit(AddAuditRequestDto addAuditRequestDto);

    Page<AuditResponseDto> getAudits(AuditFilterDto filterDto);

    String getRecentModifier(String contentId);

    List<AuditResponseDto> getAuditsToExport(Long start, Long end);
}
