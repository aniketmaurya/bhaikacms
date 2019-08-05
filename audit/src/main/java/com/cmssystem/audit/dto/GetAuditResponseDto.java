package com.cmssystem.audit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetAuditResponseDto {

    private String auditId;
    private String asset;
    private String action;
    private long actionTime;
    private String modifier;
    private String oldValue;
    private String newValue;
}
