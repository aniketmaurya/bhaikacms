package com.example.scheduler.repository;

import com.example.scheduler.entity.SchedulingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulerRepo extends CrudRepository<SchedulingEntity, String> {
    //@Query(value = "update test set is_alive=false where expiry_date<?1 ", nativeQuery = true)
    Page<SchedulingEntity> findByExpiryDateLessThan(long expiryDate, Pageable page);//todo make it paginated

    //@Query(value = "update test set is_alive=true where start_date<?1 ", nativeQuery = true)
    Page<SchedulingEntity> findByStartDateLessThan(long currentDate, Pageable page);// to


}
