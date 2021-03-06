package com.coviam.metadata.repository;

import com.coviam.metadata.entity.Crew;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewRepository extends CrudRepository<Crew, String> {

    Page<Crew> findAll();
}
