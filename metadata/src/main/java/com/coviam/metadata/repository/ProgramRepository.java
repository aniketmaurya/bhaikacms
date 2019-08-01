package com.coviam.metadata.repository;

import com.coviam.metadata.entity.Program;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends CrudRepository {

    List<Program> findAll();

    List<Program> findByAuthor(String author);
}
