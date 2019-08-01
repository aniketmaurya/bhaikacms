package com.coviam.metadata.repository;

import com.coviam.metadata.entity.SingleVideo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingleVideoRepository extends CrudRepository<SingleVideo, String> {
}
