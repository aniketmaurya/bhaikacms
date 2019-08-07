package com.coviam.metadata.dto.response;

import com.coviam.metadata.entity.Episode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EpisodeResponse {
    private Episode episode;
    private Boolean isSuccessful;
}
