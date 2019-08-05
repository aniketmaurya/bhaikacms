package com.cmssystem.audit.services;

import com.cmssystem.audit.dto.AddAuditRequestDto;
import com.cmssystem.audit.dto.AuditFilterDto;
import com.cmssystem.audit.dto.GetAuditResponseDto;
import org.springframework.data.domain.Page;

public interface AuditService {

    Boolean addAudit(AddAuditRequestDto addAuditRequestDto);

    Page<GetAuditResponseDto> getAudits(AuditFilterDto filterDto);

    String getRecentModifier(String contentId);

}
