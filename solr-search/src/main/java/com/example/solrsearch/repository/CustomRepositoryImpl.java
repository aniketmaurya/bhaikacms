package com.example.solrsearch.repository;


import com.example.solrsearch.entity.Video;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleFilterQuery;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CustomRepositoryImpl implements CustomRepository {

    private static final String VIDEO_COLLECTION = "video";
    @Resource
    private SolrTemplate solrTemplate;
    @Autowired
    private SolrClient solrClient;

    @Override
    public Page<Video> search(String searchTerm, String categoryFilter, Pageable var1) {
        SimpleQuery solrQuery = new SimpleQuery(searchTerm)
                .addFilterQuery(new SimpleFilterQuery(new Criteria("path").is(categoryFilter)))
                .setPageRequest(PageRequest.of(var1.getPageNumber(), var1.getPageSize()));
        solrQuery.setRequestHandler("/videoSearch");

        Page results = solrTemplate.query(VIDEO_COLLECTION, solrQuery, Video.class);
        return results;
    }


    @Override
    public Page<Video> getAllVideos(Pageable var) {
        SimpleQuery query = new SimpleQuery("*:*")
                .setPageRequest(PageRequest.of(var.getPageNumber(), var.getPageSize()));

        return solrTemplate.query(VIDEO_COLLECTION, query, Video.class);
    }

    @Override
    public Page<Video> autoSuggest(String searchTerm, int pageNumber, int pageSize) {
        SimpleQuery query = new SimpleQuery(searchTerm).setPageRequest(PageRequest.of(pageNumber, pageSize));
        query.setRequestHandler("/autocomp");
        return solrTemplate.query(VIDEO_COLLECTION, query, Video.class);
    }
}
