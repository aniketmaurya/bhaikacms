package com.coviam.metadata.request;

import com.coviam.metadata.entity.Program;

import java.util.HashMap;
import java.util.Map;

public class SeasonRequest {

    String id;

    Program programId;

    Integer seasonNumber;

    String seasonDescription;

    // todo multiple urls: Done
    private Map<String, String> seasonImgUrls = new HashMap<>();


    // todo update it: Done
    private Map<String, String> crewList = new HashMap<>();


}
