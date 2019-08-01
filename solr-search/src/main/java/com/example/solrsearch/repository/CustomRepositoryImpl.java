package com.example.solrsearch.repository;


import com.example.solrsearch.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class CustomRepositoryImpl implements CustomRepository {

    private static final String VIDEO_COLLECTION = "video";
    @Resource
    private SolrTemplate solrTemplate;

    @Override
    public Page<Video> search(String searchTerm, Pageable var1) {
        String[] words = searchTerm.split(" ");
        Criteria conditions = createSearchConditions(words);
        SimpleQuery search = new SimpleQuery(conditions);
        //Query query = new SimpleQuery(Criteria.where("programName").contains(searchTerm));
        Page results = solrTemplate.queryForPage(VIDEO_COLLECTION, search, Video.class);
        return results;
    }

    private Criteria createSearchConditions(String[] words) {
        Criteria conditions = null;
        for (String word : words) {
            if (conditions == null) {
                conditions = new Criteria("programName").contains(word)
                        .or(new Criteria("crewList").contains(word)).or(new Criteria("keywords").contains(word))
                        .or(new Criteria("programDescription").contains(word));
            } else {
                conditions = conditions.or(new Criteria("programName").contains(word)
                        .or(new Criteria("crewList").contains(word)).or(new Criteria("keywords").contains(word))
                        .or(new Criteria("programDescription").contains(word)));
            }
        }
        return conditions;
    }

    @Override
    public Page<Video> searchContaining(String searchTerm, Pageable var1) {
        String[] words = searchTerm.split(" ");
        Criteria conditions = createSearchConditions(words);
        //SimpleQuery search = new SimpleQuery(conditions);
        Query query = new SimpleQuery(Criteria.where("programName").startsWith(searchTerm).and(Criteria.where("programName").contains(searchTerm)));
        Page results = solrTemplate.queryForPage(VIDEO_COLLECTION, query, Video.class);
        return results;
    }
}
