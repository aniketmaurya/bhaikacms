package com.coviam.metadata.repository;

import com.coviam.metadata.entity.Season;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeasonRepository extends CrudRepository<Season, String> {

    Page<Season> findByProgramId(String programId, Pageable pageable);


    Optional<List<Season>> findByProgramId(String programId);
}
