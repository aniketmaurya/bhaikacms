package com.coviam.metadata.dto.response;

import com.coviam.metadata.dto.request.ProgramRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramResponse {

    private ProgramRequest programRequest;
    private Boolean isSuccessful;
}


