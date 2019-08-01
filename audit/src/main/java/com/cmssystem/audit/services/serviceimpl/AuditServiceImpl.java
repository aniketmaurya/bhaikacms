package com.cmssystem.audit.services.serviceimpl;

import com.cmssystem.audit.dto.AddAuditDto;
import com.cmssystem.audit.dto.AddAuditResponseDto;
import com.cmssystem.audit.dto.AuditDto;
import com.cmssystem.audit.dto.AuditPageDto;
import com.cmssystem.audit.entity.Audit;
import com.cmssystem.audit.repository.AuditRepository;
import com.cmssystem.audit.services.AuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditRepository auditRepository;


    @Override
    public AddAuditResponseDto addAudit(AddAuditDto addAuditDto) {

        Audit audit = new Audit();
        AddAuditResponseDto response = new AddAuditResponseDto();
        audit.setActionBy(addAuditDto.getActionBy());
        audit.setActionName(addAuditDto.getActionName());
        audit.setActionTime(addAuditDto.getActionTime());
        audit.setContentId(addAuditDto.getContentId());
        audit.setContentName(addAuditDto.getContentName());
        audit = auditRepository.save(audit);
        if (audit == null) {
            response.setAdded(false);
            response.setMessage("Audit cannot be logged!");
        } else {
            response.setAdded(true);
            response.setMessage("Audit logged successfully");
        }
        return response;
    }

    @Override
    public List<AuditDto> getAllAudits(AuditPageDto pageDto) {
        Page<Audit> page = auditRepository.findAll(new PageRequest(pageDto.getPageNumber(), pageDto.getCountInPage(),
                new Sort(Sort.Direction.DESC, "actionTime")));
        List<Audit> auditList = page.getContent();
        List<AuditDto> auditDtoList = new ArrayList<>();
        for (Audit audit : auditList) {
            AuditDto auditDto = new AuditDto();
            auditDto.setAuditId(audit.getAuditId());
            auditDto.setActionBy(audit.getActionBy());
            auditDto.setActionName(audit.getActionName());
            auditDto.setContentId(audit.getContentId());
            auditDto.setContentName(audit.getContentName());
            auditDtoList.add(auditDto);
        }
        return auditDtoList;
    }

    @Override
    public List<AuditDto> getRecentAudits(Integer size) {
        Page<Audit> page = auditRepository.findAll(new PageRequest(0, size,
                new Sort(Sort.Direction.DESC, "actionTime")));
        List<Audit> auditList = page.getContent();
        List<AuditDto> auditDtoList = new ArrayList<>();
        for (Audit audit : auditList) {
            AuditDto auditDto = new AuditDto();
            auditDto.setAuditId(audit.getAuditId());
            auditDto.setActionBy(audit.getActionBy());
            auditDto.setActionName(audit.getActionName());
            auditDto.setContentId(audit.getContentId());
            auditDto.setContentName(audit.getContentName());
            auditDtoList.add(auditDto);
        }
        return auditDtoList;
    }

}
