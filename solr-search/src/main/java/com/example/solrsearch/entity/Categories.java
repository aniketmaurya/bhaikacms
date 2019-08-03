package com.example.solrsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Categories {

    @Field
    String categoryId;
    @Field
    String parent;
    @Field
    String categoryName;
}
