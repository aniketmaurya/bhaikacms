package com.coviam.metadata.request;

import com.coviam.metadata.entity.Season;

import java.util.HashMap;
import java.util.Map;

public class EpisodeRequest {
    private String id;

    private Season seasonId;

    private Integer episodeNumber;

    private String episodeDescription;

    private String episodeVideoUrl;

    // todo multiple imgs: Done
    private Map<String, String> episodeImageUrls = new HashMap<>();


}