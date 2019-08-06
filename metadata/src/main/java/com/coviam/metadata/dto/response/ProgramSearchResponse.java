package com.coviam.metadata.dto.response;

import com.coviam.metadata.entity.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
public class ProgramSearchResponse {
    private String id;

    // 1->single video, 2->multi 3->seasonal
    private String type;

    private String description;

    private String name;

    private String parentalRating;

    // we will store keywords as space separated values
    private List<String> keywords;

    // We will languages as space separated values
    private List<String> languages;

    private Long startDate;

    private Long expiryDate;

    private Category category;

    private Map<String, String> imgUrls = new HashMap<>();

    private Long creationDate;

}
