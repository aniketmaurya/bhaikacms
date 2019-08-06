package com.coviam.metadata.dto.request;

import com.coviam.metadata.entity.Program;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Data
@Getter
@Setter
@Builder
public class SeasonRequest {

    private String id;

    private Program program;

    private String seasonName;

    private Integer seasonNumber;

    private String seasonDescription;

    private Map<String, String> seasonImgUrls = new HashMap<>();

    private Long creationDate;

    private String userEmail;

    private String userId;


}
