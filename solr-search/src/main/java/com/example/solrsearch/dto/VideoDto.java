package com.example.solrsearch.dto;

import com.example.solrsearch.entity.Categories;
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
public class VideoDto {

    List<String> keywords;
    String videoUrl;
    Map<String, String> crewList;
    List<String> languages;
    String programDescription;
    String videoType;
    private String programId;
    private String programName;
    private List<Categories> categoriesList;

}
