package com.coviam.metadata.repository;

import com.coviam.metadata.entity.Episode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpisodeRepository extends CrudRepository {

    List<Episode> findBySeasonId(String SeasonId);
}
