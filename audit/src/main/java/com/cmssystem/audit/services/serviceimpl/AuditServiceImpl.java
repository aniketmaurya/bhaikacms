package com.cmssystem.audit.services.serviceimpl;

import com.cmssystem.audit.dto.AddAuditRequestDto;
import com.cmssystem.audit.dto.AuditFilterDto;
import com.cmssystem.audit.dto.Changes;
import com.cmssystem.audit.dto.GetAuditResponseDto;
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
    public Boolean addAudit(AddAuditRequestDto addAuditRequestDto) {

        log.debug("addAudit in AuditServiceImpl");
        String oldValue = "", newValue = "";
        for (Changes changes : addAuditRequestDto.getChanges()) {
            oldValue = oldValue.concat(changes.getFieldChanged()).concat(":").concat(changes.getOldValue()).concat(", ");
            newValue = newValue.concat(changes.getFieldChanged()).concat(":").concat(changes.getNewValue()).concat(", ");
        }

        Audit audit = Audit.builder()
                .action(addAuditRequestDto.getAction())
                .actionTime(System.currentTimeMillis())
                .asset(addAuditRequestDto.getAsset())
                .assetId(addAuditRequestDto.getAssetId())
                .modifier(addAuditRequestDto.getModifier())
                .oldValue(oldValue.substring(0, oldValue.length() - 2))
                .newValue(newValue.substring(0, newValue.length() - 2))
                .build();

        log.debug("Audit Old data: {}", audit.getOldValue());
        log.debug("Audit New data: {}", audit.getNewValue());

        try {
            auditRepository.save(audit);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("Error is: {}", e.toString());
            return Boolean.FALSE;
        }

    }

    @Override
    public Page<GetAuditResponseDto> getAudits(AuditFilterDto filterDto) {

        log.debug("Came into getAudits");

        String actionBy = (filterDto.getUserId() == null || filterDto.getUserId().trim().length() == 0) ? "" : filterDto.getUserId().trim();
        Long start, end;
        if (filterDto.getStartDate() == null && filterDto.getEndDate() == null) {
            start = (long) -1;
            end = (long) -1;
        } else {
            start = (filterDto.getStartDate() == null) ? 0 : filterDto.getStartDate();
            end = (filterDto.getEndDate() == null) ? System.currentTimeMillis() : filterDto.getEndDate();
        }
        Integer pageSize = filterDto.getPageSize() == null ? 10 : filterDto.getPageSize();
        Integer pageNumber = filterDto.getPageNumber() == null ? 0 : filterDto.getPageNumber();

        log.debug("RequestDto:: {}", filterDto);
        log.debug("Converted:: by: {}  start: {}  end: {}  size: {}  pg: {}", actionBy, start, end, pageSize, pageNumber);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "actionTime"));

        Page<Audit> page;
        if (StringUtils.isEmpty(actionBy) && !(start == -1 && end == -1)) {
            page = auditRepository.findByActionTimeBetween(start, end, pageRequest);
            log.debug("Actionby null & date is {} & {}.", start, end);
        } else if (StringUtils.isEmpty(actionBy) && (start == -1 && end == -1)) {
            page = auditRepository.findAll(pageRequest);
            log.debug("Actionby is null & date not provided.");
        } else if (!StringUtils.isEmpty(actionBy) && (start == -1 && end == -1)) {
            page = auditRepository.findByModifierAndActionTimeBetween(actionBy, start, end, pageRequest);
            log.debug("Actionby is {} & date is {} & {}", actionBy, start, end);
        } else {
            page = auditRepository.findByModifier(actionBy, pageRequest);
            log.debug("Actionby is {} & date is null.", actionBy);
        }


        return page.map(this::convertAuditToDto);
    }

    @Override
    public String getRecentModifier(String contentId) {
        return auditRepository.findRecentModifierByAssetId(contentId);
    }

    private GetAuditResponseDto convertAuditToDto(Audit audit) {
        return GetAuditResponseDto.builder()
                .auditId(audit.getAuditId())
                .action(audit.getAction())
                .actionTime(audit.getActionTime())
                .asset(audit.getAsset().concat("(").concat(audit.getAssetId()).concat(")"))
                .modifier(audit.getModifier())
                .oldValue(audit.getOldValue())
                .newValue(audit.getNewValue())
                .build();
    }


}
