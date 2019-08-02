package com.coviam.metadata.response;

import com.coviam.metadata.entity.Program;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramResponse {
    private Program program;
    private boolean isSuccessful;
}
