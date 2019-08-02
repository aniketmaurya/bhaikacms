package com.cmssystem.audit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditRequestDto {
    private Integer pageNumber;
    private Integer pageSize;
    private Long startDate;
    private Long endDate;
    private String userId;
}
