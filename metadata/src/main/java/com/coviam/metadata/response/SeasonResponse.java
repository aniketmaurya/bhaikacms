package com.coviam.metadata.response;

import com.coviam.metadata.entity.Season;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeasonResponse {

    private Season season;
    private Boolean isSuccessful;

}
