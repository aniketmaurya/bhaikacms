package com.coviam.metadata.repository;

import com.coviam.metadata.entity.Program;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends CrudRepository<Program, String> {

    Page<Program> findByType(String type, Pageable pageable);




}
