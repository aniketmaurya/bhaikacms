package com.bhaikacms.scheduler.repository;

import com.bhaikacms.scheduler.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,String> {
}
