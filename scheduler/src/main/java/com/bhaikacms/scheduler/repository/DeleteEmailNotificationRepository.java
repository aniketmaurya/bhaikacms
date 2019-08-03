package com.bhaikacms.scheduler.repository;

import com.bhaikacms.scheduler.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface DeleteEmailNotificationRepository extends CrudRepository<User,String> {

     @Query(value = "SELECT * FROM USERDB WHERE exp_date <= ?1",nativeQuery = true)
    public List<User> findByExpDate(@Param("timestamp") Timestamp timestamp);
}
