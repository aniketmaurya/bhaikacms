package com.coviam.metadata.request;

import com.coviam.metadata.entity.Season;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EpisodeRequest {
    private String id;

    private Season seasonId;

    private Integer episodeNumber;

    private String episodeDescription;

    private String episodeVideoUrl;

    // todo multiple imgs: Done
    private Map<String, String> episodeImageUrls = new HashMap<>();


}