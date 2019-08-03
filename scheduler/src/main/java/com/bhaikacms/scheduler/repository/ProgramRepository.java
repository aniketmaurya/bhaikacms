package com.bhaikacms.scheduler.repository;

import com.bhaikacms.scheduler.entity.Program;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository extends CrudRepository<Program ,String> {
}
