package com.coviam.metadata.dto.response;

import com.coviam.metadata.entity.Category;
import com.coviam.metadata.utility.SubCategories;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
public class ProgramSearchResponse {
    private List<String> keywords;
    private String videoUrl;
    private Map<String, String> crewList;
    private List<String> languages;
    private String programDescription;
    private String videoType;
    private String programId;
    private String programName;
    private Category category;
}