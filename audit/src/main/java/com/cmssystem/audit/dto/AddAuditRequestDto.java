package com.cmssystem.audit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddAuditRequestDto {
    private String asset;
    private String assetId;
    private String action;
    private String modifier;
    private List<Changes> changes;
}
