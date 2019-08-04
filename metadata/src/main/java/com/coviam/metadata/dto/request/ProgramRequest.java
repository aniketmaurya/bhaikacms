package com.coviam.metadata.dto.request;

import com.coviam.metadata.entity.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
@Slf4j
@Getter
@Setter
public class ProgramRequest implements Serializable {
    private String id;

    private String type;
    private String description;
    private String name;
    private String parentalRating;

    // we will store keywords as space separated values
    private String keywords;

    // We will languages as space separated values
    private String languages;
    private Long startDate;
    private Long expiryDate;
    private Boolean isAlive;

    // to store which user has uploaded this file
    private String userId;

    //    private String author;
    private Category category;

    private Map<String, String> imgUrls = new HashMap<>();
}
