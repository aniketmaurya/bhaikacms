package com.bhaikacms.scheduler.services;

import com.bhaikacms.scheduler.entity.User;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface DeleteEmailNotification {
    public List<User> findAll();
    public List<User> findByExpDate();
}
