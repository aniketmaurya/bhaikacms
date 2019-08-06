package com.coviam.metadata.repository;

import com.coviam.metadata.entity.Episode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpisodeRepository extends CrudRepository<Episode, String> {

    Page<Episode> findBySeasonId(String SeasonId, Pageable pageable);

    void deleteById(String episodeId);

    void deleteAllBySeasonId(String seasonId);

    @Query(value = "SELECT season_id from public.episode where episode_title = ?1", nativeQuery = true)
    String findSeasonIdFromEpisodeTitle(String episodeTitle);
}
