package com.cmssystem.audit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuditDto {
    private String auditId;
    private String contentId;
    private String contentName;
    private String actionName;
    private Timestamp actionTime;
    private String actionBy;
}
