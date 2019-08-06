package com.coviam.metadata.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class EmailResponse {
    private String id;
    private String userId;
    private Long startDate;
    private Long expiryDate;

}
