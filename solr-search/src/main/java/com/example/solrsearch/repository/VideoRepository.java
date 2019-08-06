package com.example.solrsearch.repository;

import com.example.solrsearch.entity.Video;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VideoRepository extends CustomRepository, SolrCrudRepository<Video, String> {

    // Video findByProgramId(String programId);
}
