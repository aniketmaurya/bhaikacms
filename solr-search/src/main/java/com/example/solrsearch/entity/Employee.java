package com.example.solrsearch.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SolrDocument(solrCoreName = "employee")
public class Employee {

    @Id
    @Field
    private String  employeeId;
    @Field
    private String name;
}
