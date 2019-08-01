package com.coviam.metadata.repository;

import com.coviam.metadata.entity.Season;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepository extends CrudRepository<Season, String> {


    List<Season> save(Season season);

}