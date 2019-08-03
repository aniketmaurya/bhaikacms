package com.example.solrsearch.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Dynamic;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(collection = "video")
@Builder
public class Video {

    @Indexed(stored = true, searchable = true, type = "keywords")
    List<String> keywords;
    @Indexed(stored = true, searchable = true, type = "text_general")
    String videoUrl;
    @Dynamic
    @Indexed(value = "crewList_*", copyTo = "crewListCopy", type = "programName")
    Map<String, String> crewList;
    @Indexed(stored = true, searchable = true, type = "languages")
    List<String> languages;
    @Indexed(stored = true, searchable = true, type = "programDescription")
    String programDescription;
    @Indexed(stored = true, searchable = true, type = "videotype")
    String videoType;
    @Id
    @Indexed(stored = true)
    private String programId;
    @Indexed(stored = true, searchable = true, type = "programName")
    private String programName;
    @Indexed(stored = true, searchable = true, type = "categoryList")
    private List<Categories> categoriesList;
    @Indexed(stored = true, searchable = true, type = "programName")
    private String crewListCopy;

}
