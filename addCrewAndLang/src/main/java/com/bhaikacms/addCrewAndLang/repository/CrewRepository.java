package com.bhaikacms.addCrewAndLang.repository;

import com.bhaikacms.addCrewAndLang.entity.Crew;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewRepository extends CrudRepository<Crew,Integer> {
    Crew findByCrewName(String crewName);
}
