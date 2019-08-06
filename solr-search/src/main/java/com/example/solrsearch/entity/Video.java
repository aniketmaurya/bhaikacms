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
    private List<String> keywords;
    @Dynamic
    @Indexed(value = "crewList_*", copyTo = "crewListCopy", type = "name")
    private Map<String, String> crewList;
    @Indexed(stored = true, searchable = true, type = "languages")
    private List<String> languages;
    @Indexed(stored = true, searchable = true, type = "description")
    private String description;
    @Indexed(stored = true, searchable = true, type = "videotype")
    private String type;
    @Id
    @Indexed(stored = true)
    private String id;
    @Indexed(stored = true, searchable = true, type = "name")
    private String name;
    @Indexed(stored = true, searchable = true, type = "categoryList")
    private List<String> categoriesList;
    @Indexed(stored = true, searchable = true, type = "name")
    private List<String> crewListCopy;
    @Indexed(stored = true, searchable = true, type = "autocomp")
    private List<String> autocomp;
    @Indexed(stored = true, searchable = true, type = "name")
    private String path;
    @Dynamic
    @Indexed(stored = true, value = "imageUrls_*", type = "programName")
    private Map<String, String> imgUrls;
    @Indexed(stored = true)
    private Long startDate;
    @Indexed(stored = true)
    private Long expiryDate;
    @Indexed(stored = true)
    private Long creationDate;
    @Indexed(stored = true)
    private String parentalRating;


}
//./server/scripts/cloud-scripts/zkcli.sh -zkhost 127.0.0.1:9983 -cmd upconfig -confname video_config2 -confdir server/solr/configsets/_default/conf