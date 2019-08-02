package com.cmssystem.audit.services;

import com.cmssystem.audit.dto.AddAuditResponseDto;
import com.cmssystem.audit.dto.AuditDto;
import com.cmssystem.audit.dto.AuditRequestDto;
import org.springframework.data.domain.Page;

public interface AuditService {

    AddAuditResponseDto addAudit(AuditDto auditDto);

    Page<AuditDto> getAudits(AuditRequestDto pageDto);

}
