package com.example.solrsearch.repository;


import com.example.solrsearch.entity.Video;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Repository
public class CustomRepositoryImpl implements CustomRepository {

    private static final String VIDEO_COLLECTION = "video";
    @Resource
    private SolrTemplate solrTemplate;
    private final List<String> solrFieldsToSearch = Arrays.asList(
            "programName",
            "crewList",
            "programDescription",
            "keywords"

    );
    @Autowired
    private SolrClient solrClient;

    @Override
    public Page<Video> search(String searchTerm, Pageable var1) {
//        Criteria conditions=createSearchConditions(searchTerm);
//        SimpleQuery query=new SimpleQuery(conditions);
//        query.setRows(var1.getPageSize());
//        query.setOffset((long)var1.getPageNumber());

        SimpleQuery solrQuery = new SimpleQuery(searchTerm);
        //.addFilterQuery(new SimpleFilterQuery(Criteria.where("crewList_*").is(searchTerm)));
        solrQuery.setRequestHandler("/videoSearch");

        Page results = solrTemplate.query(VIDEO_COLLECTION, solrQuery, Video.class);
        return results;
    }

    private Criteria createSearchConditions(String searchTerm) {
        Criteria conditions = null;


        conditions = new Criteria("programName").is(searchTerm)
                .or(new Criteria("crewList").is(searchTerm)).or(new Criteria("keywords").is(searchTerm))
                .or(new Criteria("programDescription").is(searchTerm));


        return conditions;
    }

    private void populatedQ(SimpleQuery query, String searchTerm, List<String> fieldsToSearch) {


        if (!StringUtils.isBlank(searchTerm)) {
            for (String field : fieldsToSearch) {
                query.addCriteria(Criteria.where(field).is(searchTerm));
            }
        } else {
            for (String field : fieldsToSearch) {
                query.addCriteria(Criteria.where(field).is(Criteria.WILDCARD));
            }
        }

    }

    @Override
    public Page<Video> getAllVideos(Pageable var) {
        SimpleQuery query = new SimpleQuery("*:*")
                .setPageRequest(PageRequest.of(var.getPageNumber(), var.getPageSize()));

        return solrTemplate.query(VIDEO_COLLECTION, query, Video.class);
    }

    // private void populateFQ() {

    //dismax
    //dynamic fields
    //
    // }
}
