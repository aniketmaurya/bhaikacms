package com.cmssystem.audit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditFilterDto {
    private Integer pageNumber;
    private Integer pageSize;
    private Long startDate;
    private Long endDate;
    private String modifier;
    private String sortBy;
    private Integer sortOrder;//0->ASC 1->DESC
}
