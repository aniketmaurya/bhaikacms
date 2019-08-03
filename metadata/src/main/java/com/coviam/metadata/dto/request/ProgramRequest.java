package com.coviam.metadata.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Getter
@Setter
public class ProgramRequest {

    private String id;

    private String programType;

    private String description;

    private String programName;

    private String parentalRating;

    // we will store keywords as space separated values
    private String keywords;

    // We will languages as space separated values
    private String languageId;

    private String programImgUrl;

    private Date startDate;
    private Date expiryDate;

    private Boolean isAlive;

    // to store which user has uploaded this file
    private String userId;

    private String author;

    private Map<String, String> videoImgUrls = new HashMap<>();

}