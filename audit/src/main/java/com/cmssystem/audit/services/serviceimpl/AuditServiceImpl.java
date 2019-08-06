package com.cmssystem.audit.services.serviceimpl;

import com.cmssystem.audit.dto.AddAuditRequestDto;
import com.cmssystem.audit.dto.AuditFilterDto;
import com.cmssystem.audit.dto.AuditResponseDto;
import com.cmssystem.audit.dto.Changes;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class AuditServiceImpl implements AuditService {


    private static final List<String> ALLOWED_SORT = Arrays.asList("asset", "action", "actionTime", "modifier");
    @Autowired
    private AuditRepository auditRepository;

    @Override
    public Boolean addAudit(AddAuditRequestDto addAuditRequestDto) {

        log.debug("addAudit in AuditServiceImpl");

        String oldValue = "", newValue = "";

        if (addAuditRequestDto.getChanges() == null || addAuditRequestDto.getChanges().size() == 0) {
            oldValue = "-";
            newValue = "-";
        } else {
            for (Changes changes : addAuditRequestDto.getChanges()) {
                oldValue = oldValue.concat(changes.getFieldChanged().toUpperCase())
                        .concat(": ").concat(changes.getOldValue()).concat(", ");
                newValue = newValue.concat(changes.getFieldChanged().toUpperCase())
                        .concat(": ").concat(changes.getNewValue()).concat(", ");
            }
            oldValue = oldValue.substring(0, oldValue.length() - 2);
            newValue = newValue.substring(0, newValue.length() - 2);
        }

        Audit audit = Audit.builder()
                .action(addAuditRequestDto.getAction())
                .actionTime(System.currentTimeMillis())
                .asset(addAuditRequestDto.getAsset())
                .assetId(addAuditRequestDto.getAssetId())
                .modifier(addAuditRequestDto.getModifier())
                .oldValue(oldValue)
                .newValue(newValue)
                .flag(addAuditRequestDto.getFlag())
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
    public Page<AuditResponseDto> getAudits(AuditFilterDto filterDto) {

        log.debug("Came into getAudits");

        String modifier = (filterDto.getModifier() == null || filterDto.getModifier().trim().length() == 0) ? "" : filterDto.getModifier().trim();

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
        String sortBy = filterDto.getSortBy() == null || !ALLOWED_SORT.contains(filterDto.getSortBy()) ? "actionTime" : filterDto.getSortBy();

        log.debug("RequestDto:: {}", filterDto);
        log.debug("Converted:: by: {}  start: {}  end: {}  size: {}  pg: {}", modifier, start, end, pageSize, pageNumber);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,
                Sort.by(filterDto.getSortOrder() == null || filterDto.getSortOrder() == 0 ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy));

        Page<Audit> page;
        if (StringUtils.isEmpty(modifier) && !(start == -1 && end == -1)) {
            page = auditRepository.findByActionTimeBetween(start, end, pageRequest);
            log.debug("Actionby null & date is {} & {}.", start, end);
        } else if (StringUtils.isEmpty(modifier) && (start == -1 && end == -1)) {
            page = auditRepository.findAll(pageRequest);
            log.debug("Actionby is null & date not provided.");
        } else if (!StringUtils.isEmpty(modifier) && (start == -1 && end == -1)) {
            page = auditRepository.findByModifierAndActionTimeBetween(modifier, start, end, pageRequest);
            log.debug("Actionby is {} & date is {} & {}", modifier, start, end);
        } else {
            page = auditRepository.findByModifier(modifier, pageRequest);
            log.debug("Actionby is {} & date is null.", modifier);
        }


        return page.map(this::convertAuditToDto);
    }

    @Override
    public String getRecentModifier(String contentId) {
        log.warn("SERVICE LEVEL:: {}", contentId);
        return auditRepository.findRecentModifierByAssetId(contentId);
    }

    @Override
    public List<AuditResponseDto> getAuditsToExport(Long start, Long end) {
        List<Audit> audits = auditRepository.findByActionTimeBetween(start, end);
        List<AuditResponseDto> auditResponseDtos = new ArrayList<>();
        for (Audit audit : audits)
            auditResponseDtos.add(convertAuditToDto(audit));
        return auditResponseDtos;
    }
/*
    @Override
    public List<AuditResponseDto> getAuditsToExport(AuditFilterDto filterDto) {

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
        String sortBy=filterDto.getSortBy() == null ? "actionTime" : filterDto.getSortBy();

        log.debug("RequestDto:: {}", filterDto);
        log.debug("Converted:: by: {}  start: {}  end: {}  size: {}  pg: {}", actionBy, start, end, pageSize, pageNumber);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,
                Sort.by(filterDto.getSortOrder()==null || filterDto.getSortOrder()==0? Sort.Direction.DESC: Sort.Direction.ASC, sortBy));

        List<Audit> page;
        if (StringUtils.isEmpty(actionBy) && !(start == -1 && end == -1)) {
            page = auditRepository.findByActionTimeBetween(start, end);
            log.debug("Actionby null & date is {} & {}.", start, end);
        } else if (StringUtils.isEmpty(actionBy) && (start == -1 && end == -1)) {
            page = (List<Audit>) auditRepository.findAll();
            log.debug("Actionby is null & date not provided.");
        } else if (!StringUtils.isEmpty(actionBy) && (start == -1 && end == -1)) {
            page = auditRepository.findByModifierAndActionTimeBetween(actionBy, start, end);
            log.debug("Actionby is {} & date is {} & {}", actionBy, start, end);
        } else {
            page = auditRepository.findByModifier(actionBy);
            log.debug("Actionby is {} & date is null.", actionBy);
        }
        List<AuditResponseDto> responseDtos=new ArrayList<>();
        for(Audit audit : page)
        responseDtos.add(convertAuditToDto(audit));

        return responseDtos;
    }
*/

    private AuditResponseDto convertAuditToDto(Audit audit) {
        String asset;
        if (audit.getFlag() == 0)
            asset = audit.getAsset().concat("(").concat(audit.getAssetId()).concat(")");
        else
            asset = audit.getAsset();
        return AuditResponseDto.builder()
                .auditId(audit.getAuditId())
                .action(audit.getAction())
                .actionTime(audit.getActionTime())
                .asset(asset)
                .modifier(audit.getModifier())
                .oldValue(audit.getOldValue())
                .newValue(audit.getNewValue())
                .build();
    }


}
