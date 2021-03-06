package com.coviam.metadata.repository;

import com.coviam.metadata.entity.SingleVideo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingleVideoRepository extends CrudRepository<SingleVideo, String> {
    List<SingleVideo> findAll();
}
