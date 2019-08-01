package com.example.solrsearch.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(solrCoreName = "video")
public class Video {

    @Id
    @Field
    private String contentId;
    @Field
    private String programName;
    @Field
    List<Crew> crewList;
    @Field
    String keywords;
    @Field
    String languages;
    @Field
    String programDescription;
    @Field
    String videoType;
    String videoUrl;

}
