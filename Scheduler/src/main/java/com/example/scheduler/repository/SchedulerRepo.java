package com.example.scheduler.repository;

import com.example.scheduler.entity.SchedulingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchedulerRepo extends CrudRepository<SchedulingEntity, String> {
    @Query(value = "select * from test where expiry_date<?1 ", nativeQuery = true)
    List<SchedulingEntity> findByExpiryDateList(long expiryDate);

    @Query(value = "select * from test where start_date<?1 ", nativeQuery = true)
    List<SchedulingEntity> findByStartDateList(long startDate);


}
