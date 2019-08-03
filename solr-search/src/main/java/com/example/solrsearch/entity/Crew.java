package com.example.solrsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Crew {
    @Field
    private String name;
    @Field
    private String value;
}
//decended path for category
//dynamic field for crew