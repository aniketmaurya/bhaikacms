package com.bhaikacms.scheduler.repository;

import com.bhaikacms.scheduler.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface DeleteProgramServiceRepository extends CrudRepository<User,String> {
    @Query(value = "DELETE FROM USERDB WHERE exp_date <= ?1",nativeQuery = true)
    public void deleteByExpDate(@Param("timestamp") Timestamp timestamp);
}
