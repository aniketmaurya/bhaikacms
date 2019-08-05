package com.example.solrsearch.dto;

import com.example.solrsearch.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProgramDto {

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
