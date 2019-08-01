package com.coviam.metadata.repository;

import com.coviam.metadata.entity.Program;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramRepository extends PagingAndSortingRepository<Program, String> {

    List<Program> findByAuthor(String author);
}
