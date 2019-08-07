package com.coviam.metadata.dto.response;

import com.coviam.metadata.dto.request.SeasonRequest;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeasonResponse {
    private SeasonRequest seasonRequest;
    private Boolean isSuccessful;
}
