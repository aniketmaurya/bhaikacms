package com.cmssystem.useradmin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class AuditRequestDto {

    private String contentId;
    private String contentName;
    private String oldContent;
    private String newContent;
    private String actionName;
    private Long actionTime;
    private String actionBy;


}
