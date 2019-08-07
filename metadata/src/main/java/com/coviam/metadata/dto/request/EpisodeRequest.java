package com.coviam.metadata.dto.request;

import com.coviam.metadata.entity.Season;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EpisodeRequest {
    private String id;

    private Season season;

    private Integer episodeNumber;

    private String episodeTitle;

    private String episodeDescription;

    private String episodeVideoUrl;

    private Map<String, String> episodeImageUrls = new HashMap<>();


}