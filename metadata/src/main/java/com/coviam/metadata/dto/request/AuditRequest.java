package com.coviam.metadata.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditRequest {
    private String actionName;
    private String contentType;
    private String contentId;
    private String contentName;
    private String oldContent;
    private String newContent;
    private Long actionTime;
}
