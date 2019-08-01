package com.cmssystem.audit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAuditResponseDto {
    private Boolean added;
    private String message;
}
