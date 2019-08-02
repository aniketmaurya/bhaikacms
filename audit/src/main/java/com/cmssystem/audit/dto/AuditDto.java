package com.cmssystem.audit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuditDto {
    private String auditId;
    private String contentId;
    private String contentName;
    private String oldContent;
    private String newContent;
    private String actionName;
    private Long actionTime;
    private String actionBy;
}
