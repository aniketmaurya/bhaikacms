package com.cmssystem.audit.services.serviceimpl;

import com.cmssystem.audit.dto.AuditDto;
import com.cmssystem.audit.dto.AuditRequestDto;
import com.cmssystem.audit.entity.Audit;
import com.cmssystem.audit.repository.AuditRepository;
import com.cmssystem.audit.services.AuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class AuditServiceImpl implements AuditService {


    @Autowired
    private AuditRepository auditRepository;


    @Override
    public Boolean addAudits(AuditDto auditDto) {

        log.debug("addAudit in AuditServiceImpl");

        //List<Audit audits=new ArrayList<>();

        //for(AuditDto auditDto: auditDtos) {

        Audit audit = Audit.builder()
                .actionBy(auditDto.getActionBy())
                .actionName(auditDto.getActionName())
                .actionTime(auditDto.getActionTime())
                .contentId(auditDto.getContentId())
                .contentName(auditDto.getContentName())
                .oldContent(auditDto.getOldContent())
                .newContent(auditDto.getNewContent())
                .build();
        // audits.add(audit);

        // }
        log.warn("Audit Old data: {}", audit.getOldContent());
        log.warn("Audit New data: {}", audit.getNewContent());

        try {
            auditRepository.save(audit);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("Error is: {}", e.toString());
            return Boolean.FALSE;
        }

    }

    @Override
    public Page<AuditDto> getAudits(AuditRequestDto requestDto) {

        log.warn("Came into getAudits");

        String actionBy = (requestDto.getUserId() == null || requestDto.getUserId().trim().length() == 0) ? "" : requestDto.getUserId().trim();
        Long start, end;
        if (requestDto.getStartDate() == null && requestDto.getEndDate() == null) {
            start = (long) -1;
            end = (long) -1;
        } else {
            start = (requestDto.getStartDate() == null) ? 0 : requestDto.getStartDate();
            end = (requestDto.getEndDate() == null) ? System.currentTimeMillis() : requestDto.getEndDate();
        }
        Integer pageSize = requestDto.getPageSize() == null ? 10 : requestDto.getPageSize();
        Integer pageNumber = requestDto.getPageNumber() == null ? 0 : requestDto.getPageNumber();

        log.warn("RequestDto:: {}", requestDto);
        log.warn("Converted:: by: {}  start: {}  end: {}  size: {}  pg: {}", actionBy, start, end, pageSize, pageNumber);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "actionTime"));

        Page<Audit> page;
        if (StringUtils.isEmpty(actionBy) && !(start == -1 && end == -1)) {
            page = auditRepository.findByActionTimeBetween(start, end, pageRequest);
            log.info("Actionby null & date is {} & {}.", start, end);
        } else if (StringUtils.isEmpty(actionBy) && (start == -1 && end == -1)) {
            page = auditRepository.findAll(pageRequest);
            log.info("Actionby is null & date not provided.");
        } else if (!StringUtils.isEmpty(actionBy) && (start == -1 && end == -1)) {
            page = auditRepository.findByActionByAndActionTimeBetween(actionBy, start, end, pageRequest);
            log.info("Actionby is {} & date is {} & {}", actionBy, start, end);
        } else {
            page = auditRepository.findByActionBy(actionBy, pageRequest);
            log.info("Actionby is {} & date is null.", actionBy);
        }


        return page.map(this::convertAuditToDto);
    }

    private AuditDto convertAuditToDto(Audit audit) {
        return AuditDto.builder()
                .actionBy(audit.getActionBy())
                .actionName(audit.getActionName())
                .actionTime(audit.getActionTime())
                .contentId(audit.getContentId())
                .contentName(audit.getContentName())
                .newContent(audit.getNewContent())
                .oldContent(audit.getOldContent())
                .build();
    }


}
