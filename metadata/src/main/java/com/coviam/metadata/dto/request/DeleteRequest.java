package com.coviam.metadata.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteRequest {
    private String id;
    private String userId;
}