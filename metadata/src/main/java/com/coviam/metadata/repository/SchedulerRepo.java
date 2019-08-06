package com.coviam.metadata.repository;

import com.coviam.metadata.entity.Scheduler;
import org.springframework.data.repository.CrudRepository;

public interface SchedulerRepo extends CrudRepository<Scheduler, String> {
}
