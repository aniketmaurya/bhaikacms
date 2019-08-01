package com.cmssystem.audit.services;

import com.cmssystem.audit.dto.AddAuditDto;
import com.cmssystem.audit.dto.AddAuditResponseDto;
import com.cmssystem.audit.dto.AuditDto;
import com.cmssystem.audit.dto.AuditPageDto;

import java.util.List;

public interface AuditService {

    AddAuditResponseDto addAudit(AddAuditDto addAuditDto);

    List<AuditDto> getAllAudits(AuditPageDto pageDto);

    List<AuditDto> getRecentAudits(Integer size);
}
